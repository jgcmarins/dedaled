
/*
* 
* Sun May 31 21:14:36 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.library.exceptions;

public class EntityNotFound extends Exception {

	private static final String MSG = new String("Entity not found!");

	public EntityNotFound() {
		super(EntityNotFound.MSG);
	}
}
