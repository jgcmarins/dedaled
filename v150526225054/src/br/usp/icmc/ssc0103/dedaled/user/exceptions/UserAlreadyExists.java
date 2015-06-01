
/*
* 
* Sun May 31 21:34:49 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.user.exceptions;

public class UserAlreadyExists extends Exception {

	private static final String MSG = new String("User already exists!");

	public UserAlreadyExists() {
		super(UserAlreadyExists.MSG);
	}
}
