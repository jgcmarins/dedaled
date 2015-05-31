
/*
* 
* Wed May 27 22:21:38 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.db;

import java.io.File;
import java.io.LineNumberReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

import br.usp.icmc.ssc0103.dedaled.db.csv.*;
import br.usp.icmc.ssc0103.dedaled.library.*;

public class LibraryDatabase {

	private final String directoryName = new String("files/");
	private final String fileName = new String("library.csv");

	private File directory;
	private File file;

	public LibraryDatabase() {
		this.directory = new File(this.directoryName);
		this.directory.mkdir();
		this.file = new File(new String(this.directoryName+this.fileName));
	}

	public synchronized Long idGenerator() {
		try {

			LineNumberReader lnr = new LineNumberReader(new FileReader(this.file));
			while(lnr.readLine() != null);

			return new Long(lnr.getLineNumber() + 1);

		} catch(FileNotFoundException fnfe) {

			return new Long(1L);

		} catch(IOException ioe) { ioe.printStackTrace(); }

		return new Long(1L);
	}

	public synchronized void insertLibraryEntity(LibraryEntity le) {
		le.setId(this.idGenerator());
		CSVWriter csvw = new CSVWriter(this.file);
		csvw.appendCSV(le.toCSV());
	}

	public synchronized ArrayList<LibraryEntity> selectAll() {
		CSVReader csvr = new CSVReader(this.file);
		return this.CSVListToLibraryEntityList(csvr.readAll());
	}

	public synchronized ArrayList<LibraryEntity> CSVListToLibraryEntityList(ArrayList<String> records) {
		ArrayList<LibraryEntity> entities = new ArrayList<LibraryEntity>();
		for(String record : records) entities.add(this.CSVtoLibraryEntity(record));
		return entities;
	}

	public synchronized LibraryEntity CSVtoLibraryEntity(String csv) {
		String[] values = csv.split(",");
		LibraryEntity le = null;
		if(values[1].equals(LibraryEntity.BOOK)) le = new Book(values);
		else if(values[1].equals(LibraryEntity.ARTICLE)) le = new Article(values);
		else if(values[1].equals(LibraryEntity.MAGAZINE)) le = new Magazine(values);

		return le;
	}

	public synchronized void updateLibraryEntity(LibraryEntity le) {
		CSVReader csvr = new CSVReader(this.file);
		ArrayList<String> records = csvr.readAll();
		records.set(le.getId().intValue() - 1, le.toCSV());
		this.file.delete();
		try {

			if(this.file.createNewFile()) {
				CSVWriter csvw = new CSVWriter(this.file);
				for(String s : records) csvw.appendCSV(s);
			}
			
		} catch(IOException ioe) { ioe.printStackTrace(); }
	}

	public synchronized ArrayList<LibraryEntity> selectAllBooks() {
		ArrayList<LibraryEntity> entities = this.selectAll();
		return new ArrayList<LibraryEntity>(
					entities.stream()
							.filter(book -> book.getType().equals(LibraryEntity.BOOK))
							.collect(Collectors.toList())
				);
	}

	public synchronized ArrayList<LibraryEntity> selectAllArticles() {
		ArrayList<LibraryEntity> entities = this.selectAll();
		return new ArrayList<LibraryEntity>(
					entities.stream()
							.filter(article -> article.getType().equals(LibraryEntity.ARTICLE))
							.collect(Collectors.toList())
				);
	}

	public synchronized ArrayList<LibraryEntity> selectAllMagazines() {
		ArrayList<LibraryEntity> entities = this.selectAll();
		return new ArrayList<LibraryEntity>(
					entities.stream()
							.filter(magazine -> magazine.getType().equals(LibraryEntity.MAGAZINE))
							.collect(Collectors.toList())
				);
	}

	public synchronized	LibraryEntity selectById(Long id) {
		ArrayList<LibraryEntity> entities = this.selectAll();
		try {
			LibraryEntity le = entities.stream()
					.filter(entity -> entity.getId().equals(id))
					.findFirst()
					.get();
			return le;
		} catch(NoSuchElementException nsee) { return null; }
	}

	public synchronized ArrayList<LibraryEntity> selectByTitle(String title) {
		ArrayList<LibraryEntity> entities = this.selectAll();
		return new ArrayList<LibraryEntity>(
					entities.stream()
							.filter(entity -> entity.getTitle().equals(title))
							.collect(Collectors.toList())
				);
	}

	public synchronized ArrayList<LibraryEntity> selectByAuthor(String author) {
		ArrayList<LibraryEntity> entities = this.selectAll();
		return new ArrayList<LibraryEntity>(
					entities.stream()
							.filter(entity -> entity.getAuthor().equals(author))
							.collect(Collectors.toList())
				);
	}

	public synchronized ArrayList<LibraryEntity> selectAllLent() {
		ArrayList<LibraryEntity> entities = this.selectAll();
		return new ArrayList<LibraryEntity>(
					entities.stream()
							.filter(entity -> entity.getLent())
							.collect(Collectors.toList())
				);
	}

	public synchronized ArrayList<LibraryEntity> selectAllLate() {
		ArrayList<LibraryEntity> entities = this.selectAll();
		return new ArrayList<LibraryEntity>(
					entities.stream()
							.filter(entity -> entity.getLate())
							.collect(Collectors.toList())
				);
	}
}
