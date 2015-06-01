
/*
* 
* Fri May 29 01:56:41 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.core;

import java.util.Date;

import br.usp.icmc.ssc0103.dedaled.date.*;
import br.usp.icmc.ssc0103.dedaled.user.*;
import br.usp.icmc.ssc0103.dedaled.library.*;

public class SystemManagement {

	private LibraryManagement lm;
	private UserManagement um;
	public SystemDate sd;

	public SystemManagement(int year, int month, int day) {
		this.sd = new SystemDate(year, month, day);
		this.lm = new LibraryManagement(this.sd);
		this.um = new UserManagement(this.sd);
	}

	public void start() {
		this.lm.updateLending();
		this.um.updatePenalties(this.lm.finder.findAllLate());
		this.run();
	}

	public void run() {
		//this.lend(new Long(1L), new Long(1L));
		this.lm.browser.browseAllLibraryEntities();
		this.um.browser.browseAllUsers();
		this.lm.browser.browseAllLate();
		this.um.browser.browseAllPenalized();
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


}
