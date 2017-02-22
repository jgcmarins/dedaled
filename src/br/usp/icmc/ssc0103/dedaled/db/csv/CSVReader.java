
/*
* 
* Wed May 27 22:00:01 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.db.csv;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class CSVReader {

	private File file;

	public CSVReader(File file) {
		this.file = file;
	}

	public synchronized ArrayList<String> readAll() {
		ArrayList<String> records = new ArrayList<String>();

		try {

			Scanner s = new Scanner(this.file);
			while(s.hasNext()) records.add(new String(s.nextLine()));
			s.close();

		} catch(IOException ioe) { ioe.printStackTrace(); }

		return records;
	}
}
