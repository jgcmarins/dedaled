
/*
* 
* Fri May 29 01:56:41 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.core;

import java.util.ArrayList;

import br.usp.icmc.ssc0103.dedaled.console.*;

public class SystemManagement {

	public LibraryManagement lm;
	public UserManagement um;

	public SystemManagement() {
		this.lm = new LibraryManagement();
		this.um = new UserManagement();
	}

	public void run() {
		this.lm.browseAllLibraryEntities();
		this.um.browseAllUsers();
	}
}
