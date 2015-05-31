
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

public class LibraryFinder {

	private LibraryDatabase ld;

	public LibraryFinder(LibraryDatabase ld) {
		this.ld = ld;
	}

	public LibraryEntity findById(Long id) {
		LibraryEntity le = this.ld.selectById(id);
		return le;
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
}