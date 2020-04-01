/*

IT19058092
A.G.S.D.Wickramarathna
G-1

*/


import java.util.InputMismatchException;
import java.util.Scanner;


//Calculations Interface
interface Calculations{
	
	void calculateRevenue(Artist ArtistObj);
	
}

//user class : basic details of all Artists and Operators
abstract class User{
	
	protected String Name;
	protected String Email;
	
	
	public User(String Name , String Email) {
		
		
		this.Name = Name ;
		this.Email = Email;
		
	}
	
	public abstract void printDeatails();

}

//Artist class : Handle Artists
class Artist extends User{
	
	private String MusicGenre;	
	protected int SongsCount;
	private String temporyName;
	private String temporyRate;
	protected String Songs[][] = new String[5][2];
	
	Scanner scanSongs = new Scanner(System.in);
	
	//parameterized constructor initialize the data members
	public Artist(String Name,String Email,String MusicGenre,int SongsCount) {
		
		super(Name,Email);
		this.MusicGenre = MusicGenre;
		this.SongsCount = SongsCount ;
		
	}
	
	//set five songs and the Rating of songs of a particular Artist
	public void setSongs() {
		
	
		try {
			
			for(int i = 0; i < SongsCount; i++ ) {
				
				for(int j = 0; j < Songs[i].length; j++ ) {
					
					switch (j) {
					
					case 0:
						System.out.println("Enter song Name : ");
						temporyName = scanSongs.nextLine();
						Songs[i][j]= temporyName;
						
						break;
						
					case 1:
						System.out.println("Enter song Rate : ");
						temporyRate = scanSongs.nextLine();
						Songs[i][j]= temporyRate;
						break;

					default:
						break;
					
					}//end of switch
					
				}//end of inner for loop
				
			}//end of outer for loop
   		
		} catch (Exception e) {
			
			System.out.println("Array index out of boundry");
			//e.printStackTrace();
		}
		
	}
	
	//print song details
	public void printSongs() {
		
		if(SongsCount != 0){
			System.out.println("Songs List");
			System.out.println("===================================================================" );
		}
		else
			System.out.println("Songs List is Empty");
	
		try {
			
			for(int i = 0; i < SongsCount; i++ ) {
				
				for(int j = 0; j < Songs[i].length; j++ ) {
					
					
					switch (j) {
					
						case 0:
							System.out.print( " Name : " + Songs[i][j] +"    ");
						
							break;
						
						case 1:
							System.out.println( " Rate : " + Songs[i][j]);
							break;

						default:
							break;

					}//end of switch

				}//end of inner for loop
				
			}//end of outer for loop
		} catch ( ArrayIndexOutOfBoundsException | NumberFormatException   e) {
			
			System.out.println("===================================================================" );
			System.out.println("Array index out of boundry");
			System.out.println("===================================================================" );
			//e.printStackTrace();
		}
		System.out.println("===================================================================" );
		System.out.println("\n");
	}

	@Override
	public void printDeatails() {
		
		System.out.println("===================================================================" );
		System.out.println("Artist Name : " + Name + "\nArtist Email : " + Email );
		System.out.println("Music Genre : " + MusicGenre + "\nNumber of songs : " + SongsCount);
		System.out.println("===================================================================" );
	}
	
}

//operator class : Handle operators
class Operator extends User implements Calculations{
	
	private String EmployeeNum;	
	private String JobTitle;
	private int NoOfDownloads;
	private boolean invalidInput = true;
	private double totalRate = 0;
	private double avgRate;
	private double revenue;
	
	Scanner scanArtistDownloads = new Scanner(System.in);
	
	
	
	//parameterized constructor initialize the data members
	public Operator(String Name,String Email,String EmployeeNum ,String JobTitle) {
		
		super(Name,Email);
		this.EmployeeNum = EmployeeNum;
		this.JobTitle = JobTitle;
		
	}
	
	@Override
	public void printDeatails() {
		
		System.out.println("===================================================================" );
		System.out.println("Operator Name : " + Name + "\nOperator Email : " + Email );
		System.out.println("Employee Number : " + EmployeeNum + "\nJobTitle : " + JobTitle );
		System.out.println("===================================================================" );
	}
	
	//implement calculateRevenue : responsible all sorts of calculations
	public void calculateRevenue(Artist ArtistObj) {
		
		if(ArtistObj.SongsCount != 0 && ArtistObj.SongsCount <= 5 && ArtistObj.SongsCount > 0 )
		{
			
		
		System.out.println("Enter download count for Artist : " + ArtistObj.Name);
		try {
			NoOfDownloads = scanArtistDownloads.nextInt();
		} catch (InputMismatchException e1) {
			
			System.out.println("===================================================================" );
			System.out.println("You have input miss matched exception");
			System.out.println("===================================================================" );
		}
		
		while(invalidInput == true) {
			
			if(NoOfDownloads < 0) {
				
				 System.out.println("Invalid inputs try Agian");
				 System.out.println("Enter download count for Artist : " + ArtistObj.Name);
				 NoOfDownloads = scanArtistDownloads.nextInt();	
			     
				 
			}else{
				
				
				try {
					for(int i = 0; i < ArtistObj.SongsCount; i++ ) {
						
						for(int j = 0; j < ArtistObj.Songs[i].length; j++ ) {
							
							
							switch (j) {
					
								case 1:
								try {
									totalRate = totalRate + Double.parseDouble(ArtistObj.Songs[i][j]);
								} catch (NumberFormatException e) {
									// TODO Auto-generated catch block
																  										System.out.println("===================================================================" );
									System.out.println("Input missed matched exeption : Song rate contains invalid number formats");
System.out.println("===================================================================" );
								}
									break;

									default:
									break;
									
							}
							
							
							
						}
						
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("===================================================================" );
					System.out.println("Exeption : Some thing went wrong SET MAXIMUM SONG COUNT TO 5\n");
					System.out.println("===================================================================" );
					//e.printStackTrace();
				}
				avgRate = totalRate / ArtistObj.SongsCount;
				revenue = avgRate * NoOfDownloads;

				System.out.println("===================================================================" );
				System.out.println("Total revenue for Artist : " + ArtistObj.Name + " is :" + revenue);
				System.out.println("===================================================================" );

				invalidInput = false;	
			}
			
		}
		
		
	}else {

		System.out.println("===================================================================" );
		System.out.println("Artist's song count is invalid. Please make it a INTEGER between 1-5");
		System.out.println("===================================================================" );	

	}
	
	
	}
	
}






//main class
public class PlayASong {

	private static String UserName;//declaring all static variables in the system
	private static String UserEmail;
	private static String ArtistMusicGenre;
	private static int ArtistmusicCount;
	private static String OperatorEmpNumber;
	private static String OperatorJonTitile;
	private static int userChoice;
	private static int operatorChoice;
	private static int ArtistCount = 0 ;
	private static int OperatorCount = 0 ;
	private static boolean OperatorLogin = false;
	private static boolean quite = false;
	private static boolean songsAdded = false;
	
	
	
	//main function
	public static void main(String[] args) {
		
		Artist artist1 =  new Artist( " " , " " , " " , 0 );//initialize default objects
		Operator operator1 = new Operator( " " , " " , " " , " ");
		
		while(quite == false) {
			System.out.println("Play A Song Automated System\n");//prompt
			
			if(ArtistCount == 0){
				
				System.out.println("Press 1 to a Artist Register");//prompt

			}else{

				System.out.println("Press 1 to a Artist login");

			}

			if(OperatorCount == 0){
				
				System.out.println("Press 1 to a Operator Register");//prompt

			}else{

				System.out.println("Press 1 to a Operator login");

			}
		System.out.println("Press -3 to a EXIT");

		Scanner loginInputs = new Scanner(System.in);
		
		try {
			userChoice = loginInputs.nextInt();
		} catch (InputMismatchException e1) {
			
			System.out.println("Input Missmatched Exeption");
		}
		
		
		
		
		
		switch (userChoice) {
			
			case 1://Artist's user feed
				
				
				if(ArtistCount == 0) {//check whether Artist is already registered
				
					System.out.println("Enter Artist's Name(Without Spaces) :") ;
					UserName = loginInputs.next();
					
					
					System.out.println("Enter Artist's Email : ");
					UserEmail = loginInputs.next();
					
					
					System.out.println("Enter Artist's Music Genre : ");
					ArtistMusicGenre = loginInputs.next();
				
					
					System.out.println("Enter Artist's Number of Songs : ");
					try {
						ArtistmusicCount = loginInputs.nextInt();
					} catch (InputMismatchException e) {
						
						System.out.println("You have input missmatched expetion for Number Of Songs");
					}
				
				artist1 =  new Artist( UserName , UserEmail , ArtistMusicGenre , ArtistmusicCount);
				System.out.println("Successully Registered\n");
				}else {//registered Artists user feed
					
					System.out.println("Press 1 to view your details\nPress 2 to View my Songs");//activities of Artists
					operatorChoice =  loginInputs.nextInt();
					
				
				
				
				
					if(operatorChoice == 1) {
					
						artist1.printDeatails();
					
					}else if (operatorChoice == 2 && songsAdded == true) {
					
						artist1.printSongs();
					
					}else if(operatorChoice == 2 && songsAdded != true) {
					
						System.out.println("Your song list has not confirmed by an Operator");
					
					}
					else {
					
						System.out.println("Wrong input");
					
					}
				
				}
				
				
				ArtistCount++;
				
				break;
			
			case 2:
				
				
				if(OperatorCount == 0 ) {//check whether Operator is already registered
					
				
					System.out.println("Enter Operator's Name(Without Spaces) :");
					UserName = loginInputs.next();
				
					System.out.println("Enter Operator's Email : ");
					UserEmail = loginInputs.next();
				
					System.out.println("Enter Operator's Employee Number(Without Spaces) :");
					OperatorEmpNumber = loginInputs.next();
				
					System.out.println("Enter Operator's Job title(Without Spaces) :");
					OperatorJonTitile = loginInputs.next();
				
					operator1 = new Operator( UserName , UserEmail , OperatorEmpNumber , OperatorJonTitile);
					System.out.println("Successully Registered\n");
				
				}else {//registered Operator's user feed
					
					//activities of Operators
					System.out.println("Press 1 to view your details\nPress 2 to Add songs to the Artist\nPress 3 to Calculate revenue for the Artist");
					operatorChoice =  loginInputs.nextInt();
					
				
				
				
				
					if(operatorChoice == 1) {
					
						operator1.printDeatails();
					
					}else if(operatorChoice == 2 && ArtistCount != 0 ) {
					
						artist1.setSongs();
						songsAdded = true;
					
					}else if(operatorChoice == 2 && ArtistCount == 0 ) {
					
						System.out.println("Error! There is no any Artist");
					
					}else if(operatorChoice == 3 && ArtistCount == 0 ) {
					
						System.out.println("Error! There is no any Artist");
					
					}else if(operatorChoice == 3 && ArtistCount != 0 && songsAdded == true) {
					
						operator1.calculateRevenue(artist1);
					
					}else if(operatorChoice == 3 && ArtistCount != 0 && songsAdded != true) {
					
						System.out.println("Error! Artist does not has any songs.");
					
					}
					else {
					
						System.out.println("Wrong Input");
					
					}
				
				
				}
				OperatorCount++;
				
				break;
		
			case -3:
				quite = true;
				System.out.println("Good Bye!");//exit the system
				break;
				
			default:
				
				System.out.println("Invalid Input! Try Agian");
				
				break;
			}
		
		
	}
		
		
	}
	
}


//end of the program
