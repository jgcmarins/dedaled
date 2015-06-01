
/*
* 
* Fri May 29 23:16:24 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.user;

import java.util.ArrayList;

public class Other extends User {

	public Other(String email, String password, String fullName) {
		super(User.OTHER, email, password, fullName);
		this.setLendingPeriod(User.OTHERPERIOD);
		this.setLendingLimit(User.OTHERLIMIT);
	}

	public Other(Long id, String type, String email, String password, String fullName,
				Integer penalty, ArrayList<Long> lendingList) {
		super(id, type, email, password, fullName, penalty, lendingList);
		this.setLendingPeriod(User.OTHERPERIOD);
		this.setLendingLimit(User.OTHERLIMIT);
	}

	public Other(String[] csv) {
		super(csv);
		this.setLendingPeriod(User.OTHERPERIOD);
		this.setLendingLimit(User.OTHERLIMIT);
	}
}
