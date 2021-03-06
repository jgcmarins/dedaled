
/*
* 
* Mon Jun  1 19:21:55 BRT 2015
* author: Joao Gustavo Cabral de Marins
* e-mail: jgcmarins@gmail.com
* 
*/

package br.usp.icmc.ssc0103.dedaled.console;

public class MenuView {

	private static final String TITLE = new String("DEDALED - Library Management System\n\n");
	private static final String BROWSELIBRARY = new String("1. Browse all library entities\n");
	private static final String BROWSEUSERS = new String("2. Browse all library users\n");
	private static final String BROWSELENT = new String("3. Browse all library entities lent\n");
	private static final String BROWSELATE = new String("4. Browse all library entities late\n");
	private static final String BROWSEPENALIZED = new String("5. Browse all users penalized\n");
	private static final String INSERTLIBRARY = new String("6. Insert new library entity\n");
	private static final String INSERTUSER = new String("7. Insert new library user\n");
	private static final String LEND = new String("8. Lend a library entity by name\n");
	private static final String RETURN = new String("9. Return a library entity by it's id\n");
	private static final String QUIT = new String("0. Quit\n");
	private static final String RESET = new String("666. Reset all library entity/user data\n");

	public static final String MENU = MenuView.TITLE+MenuView.BROWSELIBRARY+MenuView.BROWSEUSERS+MenuView.BROWSELENT+MenuView.BROWSELATE
										+MenuView.BROWSEPENALIZED+MenuView.INSERTLIBRARY+MenuView.INSERTUSER+MenuView.LEND
										+MenuView.RETURN+MenuView.QUIT+MenuView.RESET;

	public MenuView() {}

	public static void menu(String time) {
		System.out.print("\033[H\033[2J");
		System.out.println(MenuView.MENU);
		System.out.print(time+"\n"+"Type a commnad: ");
	}
}
