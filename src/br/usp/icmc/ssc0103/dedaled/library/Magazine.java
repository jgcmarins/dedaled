
/*
* 
* Thu May 28 19:45:49 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.library;

import java.util.Date;

public class Magazine extends LibraryEntity {

	public Magazine(String title, String author, long current) {
		super(LibraryEntity.MAGAZINE, title, author, current);
	}

	public Magazine(Long id, String title, String author, long current) {
		super(id, LibraryEntity.MAGAZINE, title, author, current);
	}

	public Magazine(Long id, String title, String author, Date lending, Date devolution,
		Long lentTo, boolean lent, boolean late) {
		super(id, LibraryEntity.MAGAZINE, title, author, lending, devolution, lentTo, lent, late);
	}

	public Magazine(String[] csv) {
		super(csv);
	}

	@Override
	public String toCSV() {
		return Long.toString(this.getId())+","+this.getType()+","+this.getTitle()+","+this.getAuthor()
		+","+Long.toString(this.getLending().getTime())+","+Long.toString(this.getDevolution().getTime())
		+","+Long.toString(this.getLentTo())+","+this.getLent()+","+this.getLate();
	}
}
