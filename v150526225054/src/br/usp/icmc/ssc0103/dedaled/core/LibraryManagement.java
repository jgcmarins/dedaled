
/*
* 
* Thu May 28 19:36:26 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.core;

import java.util.ArrayList;
import java.util.Date;

import br.usp.icmc.ssc0103.dedaled.db.LibraryDatabase;
import br.usp.icmc.ssc0103.dedaled.library.*;
import br.usp.icmc.ssc0103.dedaled.library.exceptions.*;

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

	public boolean entityIsAvailable(Long entityId) throws EntityNotAvailableException {
		LibraryEntity le =  this.finder.findById(entityId);
		if(le.isAvailable()) return true;
		else throw new EntityNotAvailableException(le.getTitle());
	}

	public void lentLibraryEntity(Long entityId, Date lending, Date devolution, Long userId) {
		LibraryEntity le =  this.finder.findById(entityId);
		le.setLending(lending);
		le.setDevolution(devolution);
		le.setLentTo(userId);
		le.setLent(true);
		this.ld.updateLibraryEntity(le);
	}
}
