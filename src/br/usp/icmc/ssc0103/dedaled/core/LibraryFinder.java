
/*
* 
* Sun May 31 13:16:20 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.core;

import java.util.ArrayList;

import br.usp.icmc.ssc0103.dedaled.db.LibraryDatabase;
import br.usp.icmc.ssc0103.dedaled.library.*;
import br.usp.icmc.ssc0103.dedaled.library.exceptions.*;

public class LibraryFinder {

	private LibraryDatabase ld;

	public LibraryFinder(LibraryDatabase ld) {
		this.ld = ld;
	}

	public ArrayList<LibraryEntity> findAllLibraryEntities() {
		return this.ld.selectAll();
	}

	public LibraryEntity findById(Long id) throws EntityNotFound {
		LibraryEntity le = this.ld.selectById(id);
		if(le == null) throw new EntityNotFound();
		else return le;
	}

	public ArrayList<LibraryEntity> findByTitle(String title) {
		ArrayList<LibraryEntity> entities = this.ld.selectByTitle(title);
		return entities;
	}

	public ArrayList<LibraryEntity> findByAuthor(String author) {
		ArrayList<LibraryEntity> entities = this.ld.selectByAuthor(author);
		return entities;
	}

	public ArrayList<LibraryEntity> findAllLent() {
		ArrayList<LibraryEntity> entities = this.ld.selectAllLent();
		return entities;
	}

	public ArrayList<LibraryEntity> findAllLate() {
		ArrayList<LibraryEntity> entities = this.ld.selectAllLate();
		return entities;
	}

	public LibraryEntity findFirstAvailableByTitle(String title) throws EntityNotFound {
		LibraryEntity le = this.ld.selectFirstAvailableByTitle(title);
		if(le == null) throw new EntityNotFound();
		else return le;
	}
}