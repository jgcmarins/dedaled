
/*
* 
* Tue May 26 23:27:44 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.db.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

import br.usp.icmc.ssc0103.dedaled.db.csv.CSVReader;

public class CSVWriter {

	private File file;

	public CSVWriter(File file) {
		this.file = file;
	}

	public synchronized void appendCSV(String csv) {
		try {
			PrintWriter pw = new PrintWriter(
								new BufferedWriter(
									new FileWriter(this.file, true)));
									// true parameter indicates that will write to the end of the file rather than the beginning
			pw.println(csv);
			pw.close();
		} catch(IOException ioe) { ioe.printStackTrace(); }
	}
}
