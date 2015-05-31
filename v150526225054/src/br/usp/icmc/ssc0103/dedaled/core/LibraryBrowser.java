
/*
* 
* Sun May 31 13:13:00 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.core;

import java.util.ArrayList;

import br.usp.icmc.ssc0103.dedaled.db.LibraryDatabase;
import br.usp.icmc.ssc0103.dedaled.library.*;
import br.usp.icmc.ssc0103.dedaled.console.*;

public class LibraryBrowser {

	private LibraryDatabase ld;

	public LibraryBrowser(LibraryDatabase ld) {
		this.ld = ld;
	}

	public void browseAllLibraryEntities() {
		ArrayList<LibraryEntity> entities = this.ld.selectAll();
		LibraryView lv = new LibraryView(entities);
		lv.view();
	}

	public void browseAllBooks() {
		ArrayList<LibraryEntity> books = this.ld.selectAllBooks();
		LibraryView lv = new LibraryView(books);
		lv.view();
	}

	public void browseAllArticles() {
		ArrayList<LibraryEntity> articles = this.ld.selectAllArticles();
		LibraryView lv = new LibraryView(articles);
		lv.view();
	}

	public void browseAllMagazines() {
		ArrayList<LibraryEntity> magazines = this.ld.selectAllMagazines();
		LibraryView lv = new LibraryView(magazines);
		lv.view();
	}

	public void browseById(Long id) {
		LibraryEntity le = this.ld.selectById(id);
		if(le != null) {
			ArrayList<LibraryEntity> entity = new ArrayList<LibraryEntity>();
			entity.add(le);
			LibraryView lv = new LibraryView(entity);
			lv.view();
		}
		else System.out.println("Not found!");
	}

	public void browseByTitle(String title) {
		ArrayList<LibraryEntity> entities = this.ld.selectByTitle(title);
		if(entities.size() > 0) {
			LibraryView lv = new LibraryView(entities);
			lv.view();
		}
		else System.out.println("Not found!");
	}

	public void browseByAuthor(String author) {
		ArrayList<LibraryEntity> entities = this.ld.selectByAuthor(author);
		if(entities.size() > 0) {
			LibraryView lv = new LibraryView(entities);
			lv.view();
		}
		else System.out.println("Not found!");
	}

	public void browseAllLent() {
		ArrayList<LibraryEntity> entities = this.ld.selectAllLent();
		if(entities.size() > 0) {
			LibraryView lv = new LibraryView(entities);
			lv.view();
		}
		else System.out.println("None is lent!");
	}

	public void browseAllLate() {
		ArrayList<LibraryEntity> entities = this.ld.selectAllLate();
		if(entities.size() > 0) {
			LibraryView lv = new LibraryView(entities);
			lv.view();
		}
		else System.out.println("None is late!");
	}
}
