
/*
* 
* Fri May 29 23:15:12 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.user;

import java.util.ArrayList;
import java.util.Date;

public class Student extends User {

	public Student(String email, String password, String fullName, long current) {
		super(User.STUDENT, email, password, fullName, current);
		this.setLendingPeriod(User.STUDENTPERIOD);
		this.setLendingLimit(User.STUDENTLIMIT);
	}

	public Student(Long id, String email, String password, String fullName, long current) {
		super(id, User.STUDENT, email, password, fullName, current);
		this.setLendingPeriod(User.STUDENTPERIOD);
		this.setLendingLimit(User.STUDENTLIMIT);
	}

	public Student(Long id, String type, String email, String password, String fullName,
					Date penalty, Integer penaltyDays, ArrayList<Long> lendingList) {
		super(id, type, email, password, fullName, penalty, penaltyDays, lendingList);
		this.setLendingPeriod(User.STUDENTPERIOD);
		this.setLendingLimit(User.STUDENTLIMIT);
	}

	public Student(String[] csv) {
		super(csv);
		this.setLendingPeriod(User.STUDENTPERIOD);
		this.setLendingLimit(User.STUDENTLIMIT);
	}
}
