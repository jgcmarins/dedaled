
/*
* 
* Fri May 29 01:56:41 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.core;

import java.util.Date;
import java.util.Scanner;

import br.usp.icmc.ssc0103.dedaled.date.*;
import br.usp.icmc.ssc0103.dedaled.user.*;
import br.usp.icmc.ssc0103.dedaled.library.*;
import br.usp.icmc.ssc0103.dedaled.console.*;

public class SystemManagement {

	private LibraryManagement lm;
	private UserManagement um;
	public SystemDate sd;

	public Scanner keyboard;

	public SystemManagement(int year, int month, int day) {
		this.sd = new SystemDate(year, month, day);
		this.lm = new LibraryManagement(this.sd);
		this.um = new UserManagement(this.sd);
		this.keyboard = new Scanner(System.in);
	}

	public void start() {
		this.lm.updateLending();
		this.um.updatePenalties(this.lm.finder.findAllLate());
		this.run();
	}

	public void run() {
		do {
			MenuView.menu();
		} while(ProcessMenu.process(this));
	}

	public void lend(Long userId, Long entityId) {
		try {
			if(this.lm.entityIsAvailable(entityId)) {
				try {
					if(this.um.userCanLend(userId)) {
						try {
							User user = this.um.finder.findById(userId);

							Date lending = this.sd.getCurrent();
							Date devolution = new Date(lending.getTime() + user.getLendingPeriod().longValue());

							this.lm.lentLibraryEntity(entityId, lending, devolution, userId);
							this.um.lentLibraryEntity(userId, entityId);
						} catch(Exception e) { System.out.println(e.getMessage()); }
					}
				} catch(Exception e) { System.out.println(e.getMessage()); }
			}
		} catch(Exception e) { System.out.println(e.getMessage()); }
	}

	public void lend(String email, String title) {
		User u = null;
		LibraryEntity le = null;

		try {
			u = this.um.finder.findByEmail(email);
			try {
				le = this.lm.finder.findFirstAvailableByTitle(title);
				this.lend(u.getId(), le.getId());
			} catch(Exception e) { System.out.println(e.getMessage()); }

		} catch(Exception e) { System.out.println(e.getMessage()); }
	}

	public void returnLibraryEntity(String title) {
		//TODO
	}

	public void browseAllLibraryEntities() {
		this.lm.browser.browseAllLibraryEntities();
	}

	public void browseAllUsers() {
		this.um.browser.browseAllUsers();
	}

	public void browseAllLent() {
		this.lm.browser.browseAllLent();
	}

	public void browseAllLate() {
		this.lm.browser.browseAllLate();
	}
	
	public void browseAllPenalized() {
		this.um.browser.browseAllPenalized();
	}

	public void insert(String type, String title, String author) {
		if(type.equals(LibraryEntity.BOOK)) {
			System.out.print("Thanks. Tell me book's isbn: ");
			String isbn = this.keyboard.nextLine();
			this.lm.insertNewLibraryEntity(new Book(title, author, isbn));
		}
		else if(type.equals(LibraryEntity.ARTICLE)) this.lm.insertNewLibraryEntity(new Article(title, author));
		else if(type.equals(LibraryEntity.MAGAZINE)) this.lm.insertNewLibraryEntity(new Magazine(title, author));
		else System.out.println(type+" is an invalid type!");
	}

	public void insert(String type, String email, String password, String fullName) {
		try {
			if(type.equals(User.PROFESSOR))
				this.um.insertNewUser(new Professor(email, password, fullName, this.sd.getCurrent().getTime()));
			else if(type.equals(User.STUDENT))
				this.um.insertNewUser(new Student(email, password, fullName, this.sd.getCurrent().getTime()));
			else if(type.equals(User.OTHER))
				this.um.insertNewUser(new Other(email, password, fullName, this.sd.getCurrent().getTime()));
			else System.out.println(type+" is an invalid type!");
		} catch(Exception e) { System.out.println(e.getMessage()); }
	}
}
