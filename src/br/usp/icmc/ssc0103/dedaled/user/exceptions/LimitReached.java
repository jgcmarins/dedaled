
/*
* 
* Sun May 31 19:05:06 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.user.exceptions;

public class LimitReached extends Exception {

	private static final String MSG = new String(" has reached lending limit!");

	public LimitReached(String message) {
		super("\""+message+"\""+LimitReached.MSG);
	}
}
