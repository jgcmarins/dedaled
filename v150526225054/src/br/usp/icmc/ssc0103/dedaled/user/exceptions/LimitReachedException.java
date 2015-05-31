
/*
* 
* Sun May 31 19:05:06 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.user.exceptions;

public class LimitReachedException extends Exception {

	private static final String MSG = new String(" has reached lending limit!");

	public LimitReachedException(String message) {
		super(message+LimitReachedException.MSG);
	}
}
