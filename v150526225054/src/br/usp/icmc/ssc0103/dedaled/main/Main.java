
/*
* 
* Tue May 26 22:52:22 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.main;

import br.usp.icmc.ssc0103.dedaled.core.*;

public class Main {

	public static void main(String[] args) {

		if(args.length != 3) {
			System.out.println("Usage: Dedaled.jar year month day");
			System.exit(1);
		}

		SystemManagement sm = new SystemManagement(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		sm.start();
	}
}
