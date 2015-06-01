
/*
* 
* Sun May 31 13:31:09 BRT 2015
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
import br.usp.icmc.ssc0103.dedaled.library.*;

public class UserFinder {

	private UserDatabase ud;

	public UserFinder(UserDatabase ud) {
		this.ud = ud;
	}

	public User findById(Long id) throws UserNotFound {
		User u = this.ud.selectById(id);
		if(u == null) throw new UserNotFound();
		else return u;
	}

	public User findByEmail(String email) throws UserNotFound {
		User u = this.ud.selectByEmail(email);
		if(u == null) throw new UserNotFound();
		return u;
	}

	public ArrayList<User> findAllPenalized() {
		ArrayList<User> users = this.ud.selectAllPenalized();
		return users;
	}

	public User findUserByEntityId(Long entityId) throws UserNotFound {
		User u = this.ud.selectUserByEntityId(entityId);
		if(u == null) throw new UserNotFound();
		else return u;
	}

	public Hashtable<User, LibraryEntity> findAllLate(ArrayList<LibraryEntity> entities) throws UserNotFound {
		Hashtable<User, LibraryEntity> users = new Hashtable<User, LibraryEntity>();
		entities.stream()
				.forEach(entity -> {
					try {
						users.put(this.findUserByEntityId(entity.getId()), entity);
					} catch(Exception e) {}
				});
		if(users.size() > 0) return users;
		else throw new UserNotFound();
	}
}
