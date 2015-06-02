
/*
* 
* Tue May 26 23:13:52 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.library;

import java.util.Date;

public class Book extends LibraryEntity {

	private String isbn;

	public Book(String title, String author, long current, String isbn) {
		super(LibraryEntity.BOOK, title, author, current);
		this.isbn = isbn;
	}

	public Book(Long id, String title, String author, long current, String isbn) {
		super(id, LibraryEntity.BOOK, title, author, current);
		this.isbn = isbn;
	}

	public Book(Long id, String title, String author, Date lending, Date devolution,
				Long lentTo, boolean lent, boolean late, String isbn) {
		super(id, LibraryEntity.BOOK, title, author, lending, devolution, lentTo, lent, late);
		this.isbn = isbn;
	}

	public Book(String[] csv) {
		super(csv);
		this.isbn = new String(csv[csv.length - 1]);
	}

	public void setIsbn(String isbn) { this.isbn = isbn; }

	public String getIsbn() { return this.isbn; }

	@Override
	public String toCSV() {
		return Long.toString(this.getId())+","+this.getType()+","+this.getTitle()+","+this.getAuthor()
		+","+Long.toString(this.getLending().getTime())+","+Long.toString(this.getDevolution().getTime())
		+","+Long.toString(this.getLentTo())+","+this.getLent()+","+this.getLate()+","+this.getIsbn();
	}
}
