
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
import br.usp.icmc.ssc0103.dedaled.console.*;

public class UserManagement {

	private UserDatabase ud;

	public UserManagement() {
		this.ud = new UserDatabase();
	}

	public void insertNewUser(User u) {
		this.ud.insertUser(u);
	}

	public void browseAllUsers() {
		ArrayList<User> users = this.ud.selectAll();
		UserView uv = new UserView(users);
		uv.view();
	}

	public void browseAllProfessors() {
		ArrayList<User> professors = this.ud.selectAllProfessors();
		UserView uv = new UserView(professors);
		uv.view();
	}

	public void browseAllStudents() {
		ArrayList<User> students = this.ud.selectAllStudents();
		UserView uv = new UserView(students);
		uv.view();
	}

	public void browseAllOthers() {
		ArrayList<User> others = this.ud.selectAllOthers();
		UserView uv = new UserView(others);
		uv.view();
	}

	public void browseById(Long id) {
		User u = this.ud.selectById(id);
		if(u != null) {
			ArrayList<User> user = new ArrayList<User>();
			user.add(u);
			UserView uv = new UserView(user);
			uv.view();
		}
		else System.out.println("Not found!");
	}

	public void browseByEmail(String email) {
		User u = this.ud.selectByEmail(email);
		if(u != null) {
			ArrayList<User> user = new ArrayList<User>();
			user.add(u);
			UserView uv = new UserView(user);
			uv.view();
		}
		else System.out.println("Not found!");
	}

	public void browseAllPenalized() {
		ArrayList<User> users = this.ud.selectAllPenalized();
		if(users.size() > 0) {
			UserView uv = new UserView(users);
			uv.view();
		}
		else System.out.println("Not found!");
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
