
/*
* 
* Fri May 29 23:40:33 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.core;

import java.util.ArrayList;
import java.util.Hashtable;

import br.usp.icmc.ssc0103.dedaled.db.UserDatabase;
import br.usp.icmc.ssc0103.dedaled.user.*;
import br.usp.icmc.ssc0103.dedaled.user.exceptions.*;
import br.usp.icmc.ssc0103.dedaled.date.*;
import br.usp.icmc.ssc0103.dedaled.library.*;

public class UserManagement {

	private UserDatabase ud;

	public UserBrowser browser;
	public UserFinder finder;

	private SystemDate sd;

	public UserManagement(SystemDate sd) {
		this.ud = new UserDatabase();
		this.browser = new UserBrowser(this.ud);
		this.finder = new UserFinder(this.ud);
		this.sd = sd;
	}

	public void insertNewUser(User u) throws UserAlreadyExists {
		User user = null;
		try {
			user = this.finder.findByEmail(u.getEmail());
		} catch (UserNotFound unf) { this.ud.insertUser(u); }
		if(user != null) throw new UserAlreadyExists();
	}

	public boolean userCanLend(Long userId) throws LimitReached, UserNotFound {
		User u = this.finder.findById(userId);
		if(u == null) throw new UserNotFound();
		else  {
			if(!u.isAtLimit()) {
				if(!u.isPenalized()) throw new LimitReached(u.getFullName()); // TODO UserIsPenalizedException()
				else return true;
			} else throw new LimitReached(u.getFullName());
		}
	}

	public void lentLibraryEntity(Long userId, Long entityId) throws UserNotFound {
		User u = this.finder.findById(userId);
		if(u != null) {
			u.getLendingList().add(entityId);
			this.ud.updateUser(u);
		} else throw new UserNotFound();
	}

	public void updatePenalties(ArrayList<LibraryEntity> entities) {
		this.penalize(entities);
		this.undoPenalties(entities);
	}

	public void penalize(ArrayList<LibraryEntity> entities) {
		try {
			Hashtable<User, LibraryEntity> users = this.finder.findAllLate(entities);
			users.keySet().stream()
				.forEach(user -> {
					LibraryEntity entity = users.get(user);
					user.computePenalty(this.sd.getCurrent().getTime(), entity.getDevolution().getTime());
					this.ud.updateUser(user);
				});
		} catch(Exception e) {}
	}

	public void undoPenalties(ArrayList<LibraryEntity> entities) {
		try {
			Hashtable<User, LibraryEntity> users = this.finder.findAllLate(entities);
			users.keySet().stream()
				.forEach(user -> {
					LibraryEntity entity = users.get(user);
					user.undoPenalty(this.sd.getCurrent().getTime(), entity.getId());
					this.ud.updateUser(user);
				});
		} catch(Exception e) {}
	}
}
