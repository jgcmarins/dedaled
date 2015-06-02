
/*
* 
* Fri May 29 23:08:06 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.user;

import java.util.ArrayList;
import java.util.Date;

public class Professor extends User {

	public Professor(String email, String password, String fullName, long current) {
		super(User.PROFESSOR, email, password, fullName, current);
		this.setLendingPeriod(User.PROFESSORPERIOD);
		this.setLendingLimit(User.PROFESSORLIMIT);
	}

	public Professor(Long id, String email, String password, String fullName, long current) {
		super(id, User.PROFESSOR, email, password, fullName, current);
		this.setLendingPeriod(User.PROFESSORPERIOD);
		this.setLendingLimit(User.PROFESSORLIMIT);
	}

	public Professor(Long id, String type, String email, String password, String fullName,
					Date penalty, Integer penaltyDays, ArrayList<Long> lendingList) {
		super(id, type, email, password, fullName, penalty, penaltyDays, lendingList);
		this.setLendingPeriod(User.PROFESSORPERIOD);
		this.setLendingLimit(User.PROFESSORLIMIT);
	}

	public Professor(String[] csv) {
		super(csv);
		this.setLendingPeriod(User.PROFESSORPERIOD);
		this.setLendingLimit(User.PROFESSORLIMIT);
	}
}
