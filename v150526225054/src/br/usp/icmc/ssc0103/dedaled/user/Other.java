
/*
* 
* Fri May 29 23:16:24 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.user;

import java.util.ArrayList;
import java.util.Date;

public class Other extends User {

	public Other(String email, String password, String fullName, long current) {
		super(User.OTHER, email, password, fullName, current);
		this.setLendingPeriod(User.OTHERPERIOD);
		this.setLendingLimit(User.OTHERLIMIT);
	}

	public Other(Long id, String type, String email, String password, String fullName,
				Date penalty, Integer penaltyDays, ArrayList<Long> lendingList) {
		super(id, type, email, password, fullName, penalty, penaltyDays, lendingList);
		this.setLendingPeriod(User.OTHERPERIOD);
		this.setLendingLimit(User.OTHERLIMIT);
	}

	public Other(String[] csv) {
		super(csv);
		this.setLendingPeriod(User.OTHERPERIOD);
		this.setLendingLimit(User.OTHERLIMIT);
	}
}
