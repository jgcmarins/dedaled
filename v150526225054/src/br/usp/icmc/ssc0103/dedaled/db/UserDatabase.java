
/*
* 
* Fri May 29 23:22:35 BRT 2015
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
import br.usp.icmc.ssc0103.dedaled.user.*;

public class UserDatabase {

	private final String directoryName = new String("files/");
	private final String fileName = new String("users.csv");

	private File directory;
	private File file;

	public UserDatabase() {
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

	public synchronized void insertUser(User u) {
		u.setId(this.idGenerator());
		CSVWriter csvw = new CSVWriter(this.file);
		csvw.appendCSV(u.toCSV());
	}

	public synchronized ArrayList<User> selectAll() {
		CSVReader csvr = new CSVReader(this.file);
		return this.CSVListToUserList(csvr.readAll());
	}

	public synchronized ArrayList<User> CSVListToUserList(ArrayList<String> records) {
		ArrayList<User> users = new ArrayList<User>();
		for(String record : records) users.add(this.CSVtoUser(record));
		return users;
	}

	public synchronized User CSVtoUser(String csv) {
		String[] values = csv.split(",");
		User u = null;
		if(values[1].equals(User.PROFESSOR)) u = new Professor(values);
		else if(values[1].equals(User.STUDENT)) u = new Student(values);
		else if(values[1].equals(User.OTHER)) u = new Other(values);

		return u;
	}

	public synchronized void updateUser(User u) {
		CSVReader csvr = new CSVReader(this.file);
		ArrayList<String> records = csvr.readAll();
		records.set(u.getId().intValue() - 1, u.toCSV());
		this.file.delete();
		try {

			if(this.file.createNewFile()) {
				CSVWriter csvw = new CSVWriter(this.file);
				for(String s : records) csvw.appendCSV(s);
			}
			
		} catch(IOException ioe) { ioe.printStackTrace(); }
	}

	public synchronized ArrayList<User> selectAllProfessors() {
		ArrayList<User> users = this.selectAll();
		return new ArrayList<User>(
					users.stream()
							.filter(professor -> professor.getType().equals(User.PROFESSOR))
							.collect(Collectors.toList())
				);
	}

	public synchronized ArrayList<User> selectAllStudents() {
		ArrayList<User> users = this.selectAll();
		return new ArrayList<User>(
					users.stream()
							.filter(student -> student.getType().equals(User.STUDENT))
							.collect(Collectors.toList())
				);
	}

	public synchronized ArrayList<User> selectAllOthers() {
		ArrayList<User> users = this.selectAll();
		return new ArrayList<User>(
					users.stream()
							.filter(other -> other.getType().equals(User.OTHER))
							.collect(Collectors.toList())
				);
	}

	public synchronized	User selectById(Long id) {
		ArrayList<User> users = this.selectAll();
		try {
			User u = users.stream()
					.filter(user -> user.getId().equals(id))
					.findFirst()
					.get();
			return u;
		} catch(NoSuchElementException nsee) { return null; }
	}

	public synchronized	User selectByEmail(String email) {
		ArrayList<User> users = this.selectAll();
		try {
			User u = users.stream()
					.filter(user -> user.getEmail().equals(email))
					.findFirst()
					.get();
			return u;
		} catch(NoSuchElementException nsee) { return null; }
	}

	public synchronized ArrayList<User> selectAllPenalized() {
		ArrayList<User> users = this.selectAll();
		return new ArrayList<User>(
					users.stream()
							.filter(user -> user.isPenalized())
							.collect(Collectors.toList())
				);
	}
}
