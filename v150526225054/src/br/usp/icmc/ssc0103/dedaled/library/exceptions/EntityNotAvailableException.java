
/*
* 
* Sun May 31 18:37:34 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.library.exceptions;

public class EntityNotAvailableException extends Exception {

	private static final String MSG = new String(" isn't available!");

	public EntityNotAvailableException(String message) {
		super(message+EntityNotAvailableException.MSG);
	}
}
