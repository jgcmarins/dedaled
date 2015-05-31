
/*
* 
* Fri May 29 02:18:48 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.user;

import java.util.ArrayList;

public class User {

	private Long id;
	private String type;
	private String email;
	private String password;
	private String fullName;
	private Long lendingPeriod;
	private Integer lendingLimit;
	private Integer penalty;
	private ArrayList<Long> lendingList;

	public static final String PROFESSOR = new String("PROFESSOR");
	public static final String STUDENT = new String("STUDENT");
	public static final String OTHER = new String("OTHER");

	public static final Long PROFESSORPERIOD = new Long(5184000000L);
	public static final Long STUDENTPERIOD = new Long(1296000000L);
	public static final Long OTHERPERIOD = new Long(1296000000L);

	public static final Integer PROFESSORLIMIT = new Integer(6);
	public static final Integer STUDENTLIMIT = new Integer(4);
	public static final Integer OTHERLIMIT = new Integer(2);

	public static final Long NOID = new Long(-1L);
	public static final Long NOPERIOD = new Long(0L);
	public static final Integer NOLIMIT = new Integer(0);
	public static final Integer NOPENALTY = new Integer(0);

	public User(String type, String email, String password, String fullName) {
		this.id = User.NOID;
		this.type = type;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.lendingPeriod = User.NOPERIOD;
		this.lendingLimit = User.NOLIMIT;
		this.penalty = User.NOPENALTY;
		this.lendingList = new ArrayList<Long>();
	}

	public User(Long id, String type, String email, String password, String fullName,
				Long lendingPeriod, Integer lendingLimit, Integer penalty, ArrayList<Long> lendingList) {
		this.id = id;
		this.type = type;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.lendingPeriod = lendingPeriod;
		this.lendingLimit = lendingLimit;
		this.penalty = penalty;
		this.lendingList = lendingList;
	}

	public User(String[] csv) {
		int i = 0;
		this.id = new Long(Long.parseLong(csv[i++]));
		this.type = new String(csv[i++]);
		this.email = new String(csv[i++]);
		this.password = new String(csv[i++]);
		this.fullName = new String(csv[i++]);
		this.lendingPeriod = new Long(Long.parseLong(csv[i++]));
		this.lendingLimit = new Integer(Integer.parseInt(csv[i++]));
		this.penalty = new Integer(Integer.parseInt(csv[i++]));
		this.lendingList = new ArrayList<Long>();
		while(i < csv.length) this.lendingList.add(new Long(Long.parseLong(csv[i++])));
	}

	public void setId(Long id) { this.id = id; }
	public void setType(String type) { this.type = type; }
	public void setEmail(String email) { this.email = email; }
	public void setPassword(String password) { this.password = password; }
	public void setFullName(String fullName) { this.fullName = fullName; }
	public void setLendingPeriod(Long lendingPeriod) { this.lendingPeriod = lendingPeriod; }
	public void setLendingLimit(Integer lendingLimit) { this.lendingLimit = lendingLimit; }
	public void setPenalty(Integer penalty) { this.penalty = penalty; }

	public Long getId() { return this.id; }
	public String getType() { return this.type; }
	public String getEmail() { return this.email; }
	public String getPassword() { return this.password; }
	public String getFullName() { return this.fullName; }
	public Long getLendingPeriod() { return this.lendingPeriod; }
	public Integer getLendingLimit() { return this.lendingLimit; }
	public Integer getPenalty() { return this.penalty; }
	public ArrayList<Long> getLendingList() { return this.lendingList; }

	public String toCSV() {
		String list = lendingListToCSV();
		return this.id.toString()+","+this.type+","+this.email+","+this.password
				+","+this.fullName+","+this.lendingPeriod.toString()+","+this.lendingLimit.toString()
				+","+this.penalty.toString()+","+list;
	}

	public String lendingListToCSV() {
		String list = new String("");
		for(int i = 0 ; i < this.lendingList.size() ; i++) {
			if(i < (this.lendingList.size() - 1)) list = list.concat(this.lendingList.get(i).toString()+",");
			else list = list.concat(this.lendingList.get(i).toString());
		}
		return list;
	}

	public boolean isPenalized() { return !(this.penalty.equals(User.NOPENALTY)); }

	public boolean isAtLimit() { return (this.lendingList.size() == this.lendingLimit); }
}
