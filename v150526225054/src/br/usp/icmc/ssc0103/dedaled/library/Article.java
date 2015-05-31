
/*
* 
* Thu May 28 19:47:54 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.library;

import java.util.Date;

public class Article extends LibraryEntity {

	public Article(String title, String author) {
		super(LibraryEntity.ARTICLE, title, author);
	}

	public Article(Long id, String title, String author, Date lending, Date devolution,
					Long lentTo, boolean lent, boolean late) {
		super(id, LibraryEntity.ARTICLE, title, author, lending, devolution, lentTo, lent, late);
	}

	public Article(String[] csv) {
		super(csv);
	}

	@Override
	public String toCSV() {
		return Long.toString(this.getId())+","+this.getType()+","+this.getTitle()+","+this.getAuthor()
		+","+Long.toString(this.getLending().getTime())+","+Long.toString(this.getDevolution().getTime())
		+","+Long.toString(this.getLentTo())+","+this.getLent()+","+this.getLate();
	}
}
