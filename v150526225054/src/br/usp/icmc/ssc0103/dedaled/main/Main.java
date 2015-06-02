
/*
* 
* Tue May 26 22:52:22 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.main;

import java.util.Date;
import java.text.SimpleDateFormat;

import br.usp.icmc.ssc0103.dedaled.core.*;

public class Main {

	public static void main(String[] args) {

		if(args.length != 3) {
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
			String s = sdf.format(date);
			System.out.printf("Usage: Dedaled.jar year month day\n(eg. Dedaled.jar %s)\n", s);
			System.exit(1);
		}

		SystemManagement sm = new SystemManagement(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		sm.start();
	}
}
