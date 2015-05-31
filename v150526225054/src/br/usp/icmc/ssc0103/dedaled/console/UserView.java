
/*
* 
* Sun May 31 03:03:26 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.console;

import java.util.ArrayList;

import br.usp.icmc.ssc0103.dedaled.user.*;

public class UserView {

	private ArrayList<String> columnsNames;
	private ArrayList<ArrayList<String>> rows;

	private TableView tv;

	private final String ID = new String("   ID   ");
	private final String TYPE = new String("   Type   ");
	private final String EMAIL = new String("   e-mail   ");
	private final String FULLNAME = new String("   Full name   ");
	private final String PENALTY = new String("   Penalty days   ");
	private final String LENTLIST = new String("   Lent list   ");

	public UserView(ArrayList<User> users) {
		this.columnsNames = new ArrayList<String>();
		this.addColumnsNames();
		this.rows = new ArrayList<ArrayList<String>>();
		this.createRows(users);
		this.tv = new TableView(columnsNames, rows);
	}

	private void addColumnsNames() {
		this.columnsNames.add(ID);
		this.columnsNames.add(TYPE);
		this.columnsNames.add(EMAIL);
		this.columnsNames.add(FULLNAME);
		this.columnsNames.add(PENALTY);
		this.columnsNames.add(LENTLIST);
	}

	private void createRows(ArrayList<User> users) {
		for(User user : users) this.rows.add(this.userToString(user));
	}

	private ArrayList<String> userToString(User user) {
		ArrayList<String> strings = new ArrayList<String>();
		strings.add(user.getId().toString());
		strings.add(user.getType());
		strings.add(user.getEmail());
		strings.add(user.getFullName());
		strings.add(user.getPenalty().toString());
		strings.add(user.lendingListToCSV());
		return strings;
	}

	public void view() {
		this.tv.printTable();
	}
}
