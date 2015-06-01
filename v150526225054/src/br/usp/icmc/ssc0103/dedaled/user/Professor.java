
/*
* 
* Fri May 29 23:08:06 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.user;

import java.util.ArrayList;

public class Professor extends User {

	public Professor(String email, String password, String fullName) {
		super(User.PROFESSOR, email, password, fullName);
		this.setLendingPeriod(User.PROFESSORPERIOD);
		this.setLendingLimit(User.PROFESSORLIMIT);
	}

	public Professor(Long id, String type, String email, String password, String fullName,
					Integer penalty, ArrayList<Long> lendingList) {
		super(id, type, email, password, fullName, penalty, lendingList);
		this.setLendingPeriod(User.PROFESSORPERIOD);
		this.setLendingLimit(User.PROFESSORLIMIT);
	}

	public Professor(String[] csv) {
		super(csv);
		this.setLendingPeriod(User.PROFESSORPERIOD);
		this.setLendingLimit(User.PROFESSORLIMIT);
	}
}
