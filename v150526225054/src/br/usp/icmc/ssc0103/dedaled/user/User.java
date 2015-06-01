
/*
* 
* Fri May 29 02:18:48 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.user;

import java.util.ArrayList;
import java.util.Date;

public class User {

	private Long id;
	private String type;
	private String email;
	private String password;
	private String fullName;
	private Long lendingPeriod;
	private Integer lendingLimit;
	private Date penalty;
	private Integer penaltyDays;
	private ArrayList<Long> lendingList;

	public static final String PROFESSOR = new String("PROFESSOR");
	public static final String STUDENT = new String("STUDENT");
	public static final String OTHER = new String("OTHER");

	public static final Long PROFESSORPERIOD = new Long(5184000000L);
	public static final Long STUDENTPERIOD = new Long(1296000000L);
	public static final Long OTHERPERIOD = new Long(1296000000L);
	public static final Long DAY = new Long(86400000L);

	public static final Integer PROFESSORLIMIT = new Integer(6);
	public static final Integer STUDENTLIMIT = new Integer(4);
	public static final Integer OTHERLIMIT = new Integer(2);

	public static final Long NOID = new Long(-1L);
	public static final Long NOPERIOD = new Long(0L);
	public static final Integer NOLIMIT = new Integer(0);
	public static final Integer NOPENALTY = new Integer(0);

	public User(String type, String email, String password, String fullName, long current) {
		this.id = User.NOID;
		this.type = type;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.lendingPeriod = User.NOPERIOD;
		this.lendingLimit = User.NOLIMIT;
		this.penalty = new Date(current);
		this.penaltyDays = User.NOPENALTY;
		this.lendingList = new ArrayList<Long>();
	}

	public User(Long id, String type, String email, String password, String fullName,
				Date penalty, Integer penaltyDays, ArrayList<Long> lendingList) {
		this.id = id;
		this.type = type;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.lendingPeriod = User.NOPERIOD;
		this.lendingLimit = User.NOLIMIT;
		this.penalty = penalty;
		this.penaltyDays = penaltyDays;
		this.lendingList = lendingList;
	}

	public User(String[] csv) {
		int i = 0;
		this.id = new Long(Long.parseLong(csv[i++]));
		this.type = new String(csv[i++]);
		this.email = new String(csv[i++]);
		this.password = new String(csv[i++]);
		this.fullName = new String(csv[i++]);
		this.lendingPeriod = User.NOPERIOD;
		this.lendingLimit = User.NOLIMIT;
		this.penalty = new Date(Long.parseLong(csv[i++]));
		this.penaltyDays = new Integer(Integer.parseInt(csv[i++]));
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
	public void setPenalty(Date penalty) { this.penalty = penalty; }
	public void setPenaltyDays(Integer penaltyDays) { this.penaltyDays = penaltyDays; }

	public Long getId() { return this.id; }
	public String getType() { return this.type; }
	public String getEmail() { return this.email; }
	public String getPassword() { return this.password; }
	public String getFullName() { return this.fullName; }
	public Long getLendingPeriod() { return this.lendingPeriod; }
	public Integer getLendingLimit() { return this.lendingLimit; }
	public Date getPenalty() { return this.penalty; }
	public Integer getPenaltyDays() { return this.penaltyDays; }
	public ArrayList<Long> getLendingList() { return this.lendingList; }

	public String toCSV() {
		String list = lendingListToCSV();
		return this.id.toString()+","+this.type+","+this.email+","+this.password
				+","+this.fullName+","+this.penalty.getTime()+","+
				this.penaltyDays.toString()+","+list;
	}

	public String lendingListToCSV() {
		String list = new String("");
		for(int i = 0 ; i < this.lendingList.size() ; i++) {
			if(i < (this.lendingList.size() - 1)) list = list.concat(this.lendingList.get(i).toString()+",");
			else list = list.concat(this.lendingList.get(i).toString());
		}
		return list;
	}

	public boolean isPenalized() { return this.penaltyDays.equals(User.NOPENALTY); }

	public boolean isAtLimit() { return (this.lendingList.size() == this.lendingLimit); }

	public boolean hasEntity(Long entityId) {
		return this.lendingList.stream()
					.anyMatch(id -> id.equals(entityId));
	}

	public void computePenalty(long current, long devolution) {
		long diff = current - devolution;
		int penalty = (int)(diff/(User.DAY.longValue()));
		penalty++;

		if(penalty > this.getPenaltyDays().intValue()) {
			this.setPenalty(new Date(current+diff));
			this.setPenaltyDays(new Integer(penalty));
		}
	}

	public void undoPenalty(long current, Long entityId) {
		if((this.getPenalty().getTime() < current) && !hasEntity(entityId)) {
			this.setPenalty(new Date(current));
			this.setPenaltyDays(User.NOPENALTY);
		}
	}
}
