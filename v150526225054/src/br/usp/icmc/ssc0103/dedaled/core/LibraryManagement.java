
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
import java.util.stream.Collectors;

import br.usp.icmc.ssc0103.dedaled.db.LibraryDatabase;
import br.usp.icmc.ssc0103.dedaled.library.*;
import br.usp.icmc.ssc0103.dedaled.library.exceptions.*;
import br.usp.icmc.ssc0103.dedaled.date.*;

public class LibraryManagement {

	private LibraryDatabase ld;

	public LibraryBrowser browser;
	public LibraryFinder finder;

	private SystemDate sd;

	public LibraryManagement(SystemDate sd) {
		this.ld = new LibraryDatabase();
		this.browser = new LibraryBrowser(this.ld);
		this.finder = new LibraryFinder(this.ld);
		this.sd = sd;
	}

	public void insertNewLibraryEntity(LibraryEntity le) {
		this.ld.insertLibraryEntity(le);
	}

	public boolean entityIsAvailable(Long entityId) throws EntityNotAvailable, EntityNotFound {
		LibraryEntity le =  this.finder.findById(entityId);
		if(le.isAvailable()) return true;
		else throw new EntityNotAvailable(le.getTitle());
	}

	public void lentLibraryEntity(Long entityId, Date lending, Date devolution, Long userId) throws EntityNotFound {
		LibraryEntity le =  this.finder.findById(entityId);
		if(le != null) {
			le.setLending(lending);
			le.setDevolution(devolution);
			le.setLentTo(userId);
			le.setLent(true);
			this.ld.updateLibraryEntity(le);
		} else throw new EntityNotFound();
	}

	public void updateLending() {
		ArrayList<LibraryEntity> entities = this.finder.findAllLent();
		ArrayList<LibraryEntity> late =	new ArrayList<LibraryEntity>(entities.stream()
										.filter(entity -> entity.isLate(this.sd.getCurrent().getTime()))
										.filter(entity -> !entity.getLate())
										.collect(Collectors.toList()));
		for(LibraryEntity entity : late) {
			entity.setLate(true);
			this.ld.updateLibraryEntity(entity);
		}
	}

	public void returnLibraryEntityById(Long entityId) throws EntityNotFound {
		LibraryEntity le = this.finder.findById(entityId);
		if(le != null) {
			le.setDevolution(this.sd.getCurrent());
			le.setLentTo(LibraryEntity.NOTLENT);
			le.setLent(!LibraryEntity.LENT);
			le.setLate(!LibraryEntity.LATE);
			this.ld.updateLibraryEntity(le);
		} else throw new EntityNotFound();
	}

	public void clear() {
		ArrayList<LibraryEntity> entities = this.finder.findAllLibraryEntities();
		entities.stream()
			.forEach(entity -> {
				if(entity.getType().equals(LibraryEntity.BOOK)) {
					Book book = (Book) entity;
					entity = new Book(entity.getId(), entity.getTitle(),
						entity.getAuthor(), this.sd.getCurrent().getTime(), book.getIsbn());
				}
				else if(entity.getType().equals(LibraryEntity.ARTICLE))
					entity = new Article(entity.getId(), entity.getTitle(),
						entity.getAuthor(), this.sd.getCurrent().getTime());
				else if(entity.getType().equals(LibraryEntity.MAGAZINE))
					entity = new Magazine(entity.getId(), entity.getTitle(),
						entity.getAuthor(), this.sd.getCurrent().getTime());
				this.ld.updateLibraryEntity(entity);
			});
	}
}
