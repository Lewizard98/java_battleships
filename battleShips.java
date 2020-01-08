import java.util.*;

public class battleShips{
	public static void main(String[] args){
			String usr = intro();
			boolean win = game(usr);
			gameEnd(win);
	}
	
	//introduction that runs on start up and gets the user's name
	public static String intro(){
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to battleShips");
		System.out.println("Current version is v0.3");
		
		System.out.print("Enter your name: ");
		String usr = input.nextLine();
		
		return usr;
	}
	
	//The core part of the game
	public static boolean game(String usr){
		char[][] map = placeShips(usr);
		char[][] compShips = compPlaceShips();
		char[][] compMap = buildMap();
		
		boolean win = true;
		boolean play = true;
		int enemyShips = 5;
		int usrShips =5;
		
		while (play == true){
			System.out.println("It is your turn " + usr + "! Please choose a location to attack...");
			
			Scanner input = new Scanner(System.in);
			System.out.print("X(0-9): ");
			int x = input.nextInt()+1;
			System.out.print("Y(0-9): ");
			int y = input.nextInt()+1;
			
			if (compMap[x][y]=='X'||compMap[x][y]=='O'){
				System.out.println("You have already attacked that location");
				while (compMap[x][y]=='X'||compMap[x][y]=='O'){
					System.out.println("Please enter a new location to attack...");
					System.out.print("X(0-9): ");
					x = input.nextInt()+1;
					System.out.print("Y(0-9): ");
					y = input.nextInt()+1;
				}
			} else if (compShips[x][y]=='@'){
				compMap[x][y] = 'X';
				compShips[x][y] = 'X';
				System.out.println("HIT!");
				enemyShips--;
			} else {
				compMap[x][y] = 'O';
				System.out.println("MISS!");
			}
			System.out.println(usr + " remaining ships: " + usrShips + ". Opponent remaining ships: " + enemyShips + ".");
			if (enemyShips == 0) {
				play = false;
				win = true;
			} else {
				boolean compTurn = true;
				while (compTurn ==true){
					int compX = ((int) (Math.random()*(10 - 1))) + 1;
					int compY = ((int) (Math.random()*(10 - 1))) + 1;
					if (map[compX][compY]=='X'||map[compX][compY]=='O'){
						
					} else if (map[compX][compY]=='@'){
						map[compX][compY] = 'X';
						displayMap2(map, compMap);
						System.out.println("Your ship was hit!");
						usrShips--;
						compTurn = false;
					} else {
						map[compX][compY] = 'O';
						displayMap2(map, compMap);
						System.out.println("The opponent Missed your ships!");
						compTurn = false;
					}
				}
				if (usrShips == 0){
					play = false;
					win = false;
				}
			}
			
			
			
		}
		return win;
	}
	
	//placing the ships at the beginning of the game
	public static char[][] placeShips(String usr){
		char[][] map = buildMap();
		System.out.println("The map is currently empty: ");
		displayMap(map);
		int remaningships = 5;
		while (remaningships > 0){
			System.out.println("You have " + remaningships + " remaning");
			System.out.println("Enter the x and y coordintes for where you want to place your ship");
			
			Scanner input = new Scanner(System.in);
			System.out.print("X(0-9): ");
			int x = input.nextInt();
			System.out.print("Y(0-9): ");
			int y = input.nextInt();
			
			map[x+1][y+1] = '@';
			displayMap(map);
			remaningships--;
		}
		return map;
	}
	
	//building the map
	public static char[][] buildMap(){
		char[][] map = new char[12][12];
		for (int row = 0; row < 12; row++){
			map[row][0] = '|';
			map[row][11] = '|';
		}
		for (int col = 0; col <12; col++){
			map[0][col] = '-';
			map[11][col] = '-';
		}
		return map;
	}
	
	//printing the map
	public static void displayMap2(char[][] map, char[][] compMap){
		System.out.println("Home turf");
		for (int row = 0; row < 12; row++){
			for (int col = 0; col < 12; col++){
				System.out.print(map[row][col]);
			}
			System.out.print("\n");
		}
		System.out.println("Enemy territory");
		for (int row = 0; row < 12; row++){
			for (int col = 0; col < 12; col++){
				System.out.print(compMap[row][col]);
			}
			System.out.print("\n");
		}
	}
	public static void displayMap(char[][] map){
		System.out.println("Home turf");
		for (int row = 0; row < 12; row++){
			for (int col = 0; col < 12; col++){
				System.out.print(map[row][col]);
			}
			System.out.print("\n");
		}
	}
	
	//computer placeing its ships
	public static char[][] compPlaceShips(){
		char[][] compShips = buildMap();
		for (int i = 0; i < 5; ){
			int compX = ((int) (Math.random()*(10 - 1))) + 1;
			int compY = ((int) (Math.random()*(10 - 1))) + 1;
			if (compShips[compX][compY] != '@'){
				compShips[compX][compY] = '@';
				i++;
			}
		}
		return compShips;
	}
	
	//The final method that informs the user of victory of defeat
	public static void gameEnd(boolean win){
		boolean again = false;
		if (win == true){
			System.out.println("You have destroyed all of the opponent's ships! You have won!");
		} else {
			System.out.println("Your ships have all been destroyed! You have lost!");
		}
	}
}