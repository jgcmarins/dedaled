
/*
* 
* Fri May 29 23:40:33 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.core;

import java.util.ArrayList;

import br.usp.icmc.ssc0103.dedaled.db.UserDatabase;
import br.usp.icmc.ssc0103.dedaled.user.*;

public class UserManagement {

	private UserDatabase ud;

	public UserBrowser browser;
	public UserFinder finder;

	public UserManagement() {
		this.ud = new UserDatabase();
		this.browser = new UserBrowser(this.ud);
		this.finder = new UserFinder(this.ud);
	}

	public void insertNewUser(User u) {
		this.ud.insertUser(u);
	}
}
