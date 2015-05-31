
/*
* 
* Fri May 29 23:15:12 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.user;

import java.util.ArrayList;

public class Student extends User {

	public Student(String email, String password, String fullName) {
		super(User.STUDENT, email, password, fullName);
		this.setLendingPeriod(User.STUDENTPERIOD);
		this.setLendingLimit(User.STUDENTLIMIT);
	}

	public Student(Long id, String type, String email, String password, String fullName,
				Long lendingPeriod, Integer lendingLimit, Integer penalty, ArrayList<Long> lendingList) {
		super(id, type, email, password, fullName, lendingPeriod, lendingLimit, penalty, lendingList);
	}

	public Student(String[] csv) {
		super(csv);
	}
}
