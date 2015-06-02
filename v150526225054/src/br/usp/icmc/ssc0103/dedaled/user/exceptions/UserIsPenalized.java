
/*
* 
* Mon Jun  1 22:52:42 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.user.exceptions;

public class UserIsPenalized extends Exception {

	private static final String MSG = new String(" is penalized!");

	public UserIsPenalized(String message) {
		super("\""+message+"\""+UserIsPenalized.MSG);
	}
}
