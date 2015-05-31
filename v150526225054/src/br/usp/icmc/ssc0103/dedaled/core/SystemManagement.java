
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
		this.lent();
		this.lm.browser.browseAllLibraryEntities();
		this.um.browser.browseAllUsers();
	}

	public void lent() {
		Date lending = this.sd.getCurrent();
		this.lm.lentLibraryEntity(new Long(3L), lending, new Date(lending.getTime() + User.PROFESSORPERIOD), new Long(5L));
		this.um.lentLibraryEntity(new Long(5L), new Long(3L));
		this.lm.lentLibraryEntity(new Long(9L), lending, new Date(lending.getTime() + User.PROFESSORPERIOD), new Long(5L));
		this.um.lentLibraryEntity(new Long(5L), new Long(9L));
	}
}
