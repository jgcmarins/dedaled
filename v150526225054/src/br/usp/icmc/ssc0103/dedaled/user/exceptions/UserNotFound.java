
/*
* 
* Sun May 31 21:23:12 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.user.exceptions;

public class UserNotFound extends Exception {

	private static final String MSG = new String("User not found!");

	public UserNotFound() {
		super(UserNotFound.MSG);
	}
}
