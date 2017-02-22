
/*
* 
* Sun May 31 15:48:33 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.date;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SystemDate {

	private int year;
	private int month;
	private int day;

	private Calendar start;
	private Calendar current;

	public SystemDate(int year, int month, int day) {
		this.year = year;
		this.month = month - 1;
		this.day = day;
		this.start = new GregorianCalendar();
		this.current = new GregorianCalendar();
		this.organizeSystemDate();
	}

	private void organizeSystemDate() {
		this.buildStart();
		this.buildCurrent();
	}

	private void buildStart() {
		this.start = Calendar.getInstance();
		this.start.set(this.year, this.month, this.day);
	}

	private void buildCurrent() {
		this.current = Calendar.getInstance();
		this.current.set(this.year, this.month, this.day);
	}

	public Date getStart() { return this.start.getTime(); }

	public Date getCurrent() {
		this.buildCurrent();
		return this.current.getTime();
	}
}
