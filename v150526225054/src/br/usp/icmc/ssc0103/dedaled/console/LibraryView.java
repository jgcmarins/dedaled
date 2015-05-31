
/*
* 
* Sat May 30 00:39:21 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.console;

import java.util.ArrayList;

import br.usp.icmc.ssc0103.dedaled.library.*;

public class LibraryView {

	private ArrayList<String> columnsNames;
	private ArrayList<ArrayList<String>> rows;

	private TableView tv;

	private final String ID = new String("   ID   ");
	private final String TYPE = new String("   Type   ");
	private final String TITLE = new String("   Title   ");
	private final String AUTHOR = new String("   Author   ");
	private final String LENDINGDATE = new String("   Lending date   ");
	private final String DEVOLUTIONDATE = new String("   Devolution date   ");
	private final String LENTTO = new String("   Lent to   ");
	private final String LATE = new String("   Is late   ");

	public LibraryView(ArrayList<LibraryEntity> entities) {
		this.columnsNames = new ArrayList<String>();
		this.addColumnsNames();
		this.rows = new ArrayList<ArrayList<String>>();
		this.createRows(entities);
		this.tv = new TableView(columnsNames, rows);
	}

	private void addColumnsNames() {
		this.columnsNames.add(ID);
		this.columnsNames.add(TYPE);
		this.columnsNames.add(TITLE);
		this.columnsNames.add(AUTHOR);
		this.columnsNames.add(LENDINGDATE);
		this.columnsNames.add(DEVOLUTIONDATE);
		this.columnsNames.add(LENTTO);
		this.columnsNames.add(LATE);
	}

	private void createRows(ArrayList<LibraryEntity> entities) {
		for(LibraryEntity entity : entities) this.rows.add(this.entityToString(entity));
	}

	private ArrayList<String> entityToString(LibraryEntity entity) {
		ArrayList<String> strings = new ArrayList<String>();
		strings.add(entity.getId().toString());
		strings.add(entity.getType());
		strings.add(entity.getTitle());
		strings.add(entity.getAuthor());
		strings.add(entity.getLending().toString());
		strings.add(entity.getDevolution().toString());
		strings.add(entity.getLentTo().toString());
		strings.add(Boolean.toString(entity.getLate()));
		return strings;
	}

	public void view() {
		this.tv.printTable();
	}
}
