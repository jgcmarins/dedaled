
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
import br.usp.icmc.ssc0103.dedaled.user.exceptions.*;

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

	public boolean userCanLend(Long userId) throws LimitReachedException {
		User u = this.finder.findById(userId);
		if(!u.isAtLimit()) return true;
		else throw new LimitReachedException(u.getFullName());
	}

	public void lentLibraryEntity(Long userId, Long entityId) {
		User u = this.finder.findById(userId);
		u.getLendingList().add(entityId);
		this.ud.updateUser(u);
	}
}
