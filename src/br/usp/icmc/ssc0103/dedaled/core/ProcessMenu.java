
/*
* 
* Mon Jun  1 19:34:33 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.core;

import java.util.Scanner;

public class ProcessMenu {

	public ProcessMenu() {}

	public static boolean process(SystemManagement sm) {
		int operation;
		operation = sm.keyboard.nextInt();
		System.out.print("\033[H\033[2J");
		switch(operation) {
			case 0: return false;
			case 1: sm.browseAllLibraryEntities();
					break;
			case 2: sm.browseAllUsers();
					break;
			case 3: sm.browseAllLent();
					break;
			case 4: sm.browseAllLate();
					break;
			case 5: sm.browseAllPenalized();
					break;
			case 6: ProcessMenu.insertNewLibraryEntity(sm);
					break;
			case 7: ProcessMenu.insertNewUser(sm);
					break;
			case 8: ProcessMenu.lendLibraryEntity(sm);
					break;
			case 9: ProcessMenu.returnLibraryEntityById(sm);
					break;
			case 666: sm.reset();
					break;
			default: return true;
		}
		System.out.println("System time: "+sm.sd.getCurrent().toString()+"\nHit <ENTER> to continue");
		sm.keyboard.nextLine();
		sm.keyboard.nextLine();
		return true;
	}

	public static void insertNewLibraryEntity(SystemManagement sm) {
		sm.keyboard.nextLine();
		System.out.println("It is a 'BOOK', 'ARTICLE' or 'MAGAZINE'?");
		String type = sm.keyboard.nextLine();
		System.out.print("Thanks. Now tell me the title: ");
		String title =sm.keyboard.nextLine();
		System.out.print("Thanks. Now tell me author's name: ");
		String author = sm.keyboard.nextLine();
		sm.insert(type, title, author);
	}

	public static void insertNewUser(SystemManagement sm) {
		sm.keyboard.nextLine();
		System.out.println("It is a 'PROFESSOR', 'STUDENT' or 'OTHER'?");
		String type = sm.keyboard.nextLine();
		System.out.print("Thanks. Now tell me user's email: ");
		String email = sm.keyboard.nextLine();
		System.out.print("Thanks. Now tell me user's password: ");
		String password = sm.keyboard.nextLine();
		System.out.print("Thanks. Now tell me user's full name: ");
		String fullName = sm.keyboard.nextLine();
		sm.insert(type, email.toLowerCase(), password, fullName);
	}

	public static void lendLibraryEntity(SystemManagement sm) {
		sm.keyboard.nextLine();
		System.out.print("Ok. Tell me user's email: ");
		String email = sm.keyboard.nextLine();
		System.out.print("Thanks. Now tell me book's title: ");
		String title = sm.keyboard.nextLine();
		sm.lend(email, title);
	}

	public static void returnLibraryEntityById(SystemManagement sm) {
		sm.keyboard.nextLine();
		System.out.print("Ok. Tell me it's id: ");
		String entityId = sm.keyboard.nextLine();
		sm.returnLibraryEntity(new Long(Long.parseLong(entityId)));
	}
}
