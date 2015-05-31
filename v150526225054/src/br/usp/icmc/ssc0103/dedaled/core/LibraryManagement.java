
/*
* 
* Thu May 28 19:36:26 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.core;

import java.util.ArrayList;

import br.usp.icmc.ssc0103.dedaled.db.LibraryDatabase;
import br.usp.icmc.ssc0103.dedaled.library.*;

public class LibraryManagement {

	private LibraryDatabase ld;

	public LibraryBrowser browser;
	public LibraryFinder finder;

	public LibraryManagement() {
		this.ld = new LibraryDatabase();
		this.browser = new LibraryBrowser(this.ld);
		this.finder = new LibraryFinder(this.ld);
	}

	public void insertNewLibraryEntity(LibraryEntity le) {
		this.ld.insertLibraryEntity(le);
	}
}
