
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

public class SystemManagement {

	private LibraryManagement lm;
	private UserManagement um;
	public SystemDate sd;

	public SystemManagement(int year, int month, int day) {
		this.lm = new LibraryManagement();
		this.um = new UserManagement();
		this.sd = new SystemDate(year, month, day);
	}

	public void run() {
		this.lent(new Long(5L), new Long(1L));
		this.lent(new Long(5L), new Long(5L));
		this.lent(new Long(5L), new Long(2L));
		this.lent(new Long(5L), new Long(6L));
		this.lent(new Long(5L), new Long(3L));
		this.lent(new Long(5L), new Long(7L));
		this.lent(new Long(5L), new Long(4L));
		this.lent(new Long(5L), new Long(8L));
		this.lent(new Long(5L), new Long(2L));
		this.lent(new Long(5L), new Long(6L));
		this.lm.browser.browseAllLibraryEntities();
		this.um.browser.browseAllUsers();
	}

	public void lent(Long userId, Long entityId) {
		Date lending = this.sd.getCurrent();

		boolean isAvailable = false;
		boolean canLend = false;

		try {
			isAvailable = this.lm.entityIsAvailable(entityId);
		} catch(Exception e) { System.out.println(e.getMessage()); }

		try {
			canLend = this.um.userCanLend(userId);
		} catch(Exception e) { System.out.println(e.getMessage()); }

		if(isAvailable && canLend) {
			this.lm.lentLibraryEntity(entityId, lending, new Date(lending.getTime() + User.PROFESSORPERIOD), userId);
			this.um.lentLibraryEntity(userId, entityId);
		}
	}
}
