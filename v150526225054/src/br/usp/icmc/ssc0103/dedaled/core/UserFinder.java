
/*
* 
* Sun May 31 13:31:09 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.core;

import java.util.ArrayList;

import br.usp.icmc.ssc0103.dedaled.db.UserDatabase;
import br.usp.icmc.ssc0103.dedaled.user.*;

public class UserFinder {

	private UserDatabase ud;

	public UserFinder(UserDatabase ud) {
		this.ud = ud;
	}

	public User findById(Long id) {
		User u = this.ud.selectById(id);
		return u;
	}

	public User findByEmail(String email) {
		User u = this.ud.selectByEmail(email);
		return u;
	}

	public ArrayList<User> findAllPenalized() {
		ArrayList<User> users = this.ud.selectAllPenalized();
		return users;
	}
}
