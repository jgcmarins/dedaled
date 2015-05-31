
/*
* 
* Sun May 31 01:13:55 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.console;

import java.util.ArrayList;

public class TableView {

	private ArrayList<String> columnsNames;
	private ArrayList<Integer> columnsLength;

	private ArrayList<ArrayList<String>> rows;

	private String names;
	private String separator;
	private String header;

	private final String BAR = new String("|");
	private final String PLUS = new String("+");
	private final String HYPHEN = new String("-");
	private final String SPACE = new String(" ");
	private final String NEWLINE = new String("\n");

	public TableView(ArrayList<String> columnsNames, ArrayList<ArrayList<String>> rows) {
		this.columnsNames = columnsNames;
		this.addLengths();
		this.rows = rows;
		this.names = new String("");
		this.separator = new String(NEWLINE);
		this.header = new String("");
		this.buildHeader();
	}

	private void addLengths() {
		this.columnsLength = new ArrayList<Integer>();
		for(String s : this.columnsNames) this.columnsLength.add(new Integer(s.length() + 2));
	}

	private void buildHeader() {
		for(String s : this.columnsNames) this.names = this.names.concat(BAR+SPACE+s+SPACE);
		this.names = this.names.concat(BAR);

		for(int i = 0 ; i < this.names.length() ; i++) {
			if(this.names.charAt(i) == BAR.charAt(0)) this.separator = this.separator.concat(PLUS);
			else this.separator = this.separator.concat(HYPHEN);
		}
		this.separator = this.separator.concat(NEWLINE);

		this.header = this.header.concat(this.separator+this.names+this.separator);
	}

	public void printTable() {
		this.printHeader();
		this.printRows();
	}

	private void printHeader() {
		System.out.print(this.header);
	}

	private void printSeparator() {
		System.out.print(this.separator);
	}

	private void printRows() {
		for(ArrayList<String> row : this.rows) {
			ArrayList<Integer> rowsPerColumn = new ArrayList<Integer>();
			int biggestLength = this.calculateBiggestLength(row, rowsPerColumn);
			this.printRow(row, rowsPerColumn, biggestLength);
			this.printSeparator();
		}
	}

	private int calculateBiggestLength(ArrayList<String> row, ArrayList<Integer> rowsPerColumn) {
		float proportion = 1;
		for(int i = 0 ; i < row.size() ; i++) {
			proportion = (row.get(i).length()/this.columnsLength.get(i));
			rowsPerColumn.add(new Integer(Math.round(proportion) + 1));
		}

		return (rowsPerColumn.stream()
							.max((x, y) -> x.compareTo(y))
							.get()).intValue();
	}

	private void printRow(ArrayList<String> row, ArrayList<Integer> rowsPerColumn, int biggestLength) {
		int time = 1;
		for(int i = 0 ; i < row.size() ; i++) {
			String string = row.get(i);
			String substring = null;
			int length = this.columnsLength.get(i).intValue();

			if(time <= rowsPerColumn.get(i).intValue()) { // O perigo esta logo abaixo
					if(string.length() > time*length) substring = string.substring((time-1)*length, time*length);
					else substring = string.substring((time-1)*length, string.length());
					this.printColumn(substring, length, time);
				}
			else this.printEmpty(length);

			if(i == row.size() - 1) {
				time++;
				if(time <= biggestLength) {
					i = -1;
					System.out.print("|\n");
				}
			}
		}
		System.out.print("|");
	}

	private void printColumn(String string, int length, int time) {
		int proportion = ((length - string.length())/2);
		System.out.print(BAR);
		for(int i = 0 ; i < proportion ; i++) System.out.print(SPACE);
		System.out.print(string);
		for(int i = 0 ; i < proportion ; i++) System.out.print(SPACE);
		if(string.length() < length) {
			proportion *= 2;
			proportion += string.length();
			if(proportion < length) System.out.print(SPACE);
		}
	}

	private void printEmpty(int length) {
		System.out.print(BAR);
		for(int i = 0 ; i < length ; i++) System.out.print(SPACE);
	}
}
