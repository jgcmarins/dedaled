
/*
* 
* Tue May 26 22:58:21 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.library;

import java.util.Date;

public abstract class LibraryEntity {

	private Long id;
	private String type;
	private String title;
	private String author;
	private Date lending;
	private Date devolution;
	private Long lentTo;
	private boolean lent;
	private boolean late;

	public static final String BOOK = new String("BOOK");
	public static final String ARTICLE = new String("ARTICLE");
	public static final String MAGAZINE = new String("MAGAZINE");

	public static final Long NOID = new Long(-1L);
	public static final Long NOTLENT = new Long(-1L);
	public static final boolean LENT = true;
	public static final boolean LATE = true;

	public LibraryEntity(String type, String title, String author) {
		this.id = LibraryEntity.NOID;
		this.type = type;
		this.title = title;
		this.author = author;
		this.lending = new Date(System.currentTimeMillis());
		this.devolution = new Date(System.currentTimeMillis());
		this.lentTo = LibraryEntity.NOTLENT;
		this.lent = !LibraryEntity.LENT;
		this.late = !LibraryEntity.LATE;
	}

	public LibraryEntity(Long id, String type, String title, String author, Date lending,
							Date devolution, Long lentTo, boolean lent, boolean late) {
		this.id = id;
		this.type = type;
		this.title = title;
		this.author = author;
		this.lending = lending;
		this.devolution = devolution;
		this.lentTo = lentTo;
		this.lent = lent;
		this.late = late;
	}

	public LibraryEntity(String[] csv) {
		int i = 0;
		this.id = new Long(Long.parseLong(csv[i++]));
		this.type = new String(csv[i++]);
		this.title = new String(csv[i++]);
		this.author = new String(csv[i++]);
		this.lending = new Date(Long.parseLong(csv[i++]));
		this.devolution = new Date(Long.parseLong(csv[i++]));
		this.lentTo = new Long(Long.parseLong(csv[i++]));
		this.lent = Boolean.parseBoolean(csv[i++]);
		this.late = Boolean.parseBoolean(csv[i++]);
	}

	public void setId(Long id) { this.id = id; }
	public void setType(String type) { this.type = type; }
	public void setTitle(String title) { this.title = title; }
	public void setAuthor(String author) { this.author = author; }
	public void setLending(Date lending) { this.lending = lending; }
	public void setDevolution(Date devolution) { this.devolution = devolution; }
	public void setLentTo(Long lentTo) { this.lentTo = lentTo; }
	public void setLent(boolean lent) { this.lent = lent; }
	public void setLate(boolean late) { this.late = late; }

	public Long getId() { return this.id; }
	public String getType() { return this.type; }
	public String getTitle() { return this.title; }
	public String getAuthor() { return this.author; }
	public Date getLending() { return this.lending; }
	public Date getDevolution() { return this.devolution; }
	public Long getLentTo() { return this.lentTo; }
	public boolean getLent() { return this.lent; }
	public boolean getLate() { return this.late; }

	public abstract String toCSV();

	public boolean isAvailable() { return !this.lent; }

	public boolean isLate(long current) {
		//System.out.println("Current: "+current+"\nDevolution: "+this.devolution.getTime());
		if(this.devolution.getTime() < current) return true;
		else return false;
	}
}
