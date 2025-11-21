import java.util.*;
import java.io.*;
public class Main {
	//Main menu for user interaction
	public static void MainMenu(Scanner input, ArrayList<User> Users){
		while(true){
			System.out.println("Would You Like to:");
			System.out.println("1. Create Account");
			System.out.println("2. Login");
			System.out.println("3. Delete Account");
			System.out.println("4. Exit");
			int choice = Integer.parseInt(input.nextLine());
			//Switch case to handle user choice
			switch(choice){
				case 1:
					System.out.println("Creating Account...");
					CreateAccount(input, Users);
					break;
				case 2:
					Login(input, Users);
					break;
				case 3:
					DeleteAccount(input, Users);
					break;
				case 4: 
					System.out.println("Exiting...");
				try{
					SaveUsersToFile(Users);
				}catch(IOException e){
					System.out.println("Error saving users to file.");
				}
					System.exit(0);
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}
	
	//Creates account and adds to arraylist
	public static void CreateAccount(Scanner input, ArrayList<User> Users){
		System.out.println("Enter Username:");
		String UserName = input.nextLine();
		System.out.println("Enter Password:");
		String Password = input.nextLine();
		System.out.println("Enter Verification Question:");
		String VerificationQuestion = input.nextLine();
		System.out.println("Enter Verification Answer:");
		String VerificationAnswer = input.nextLine();
		User newUser = new User(UserName, Password, VerificationQuestion, VerificationAnswer);
		Users.add(newUser);
		System.out.println("Account Created Successfully!");
	}
	//Logs in user by checking username and password against arraylist
	public static void Login(Scanner input, ArrayList<User> Users){
		while(true){
		System.out.println("Enter Username:");
		String UserName = input.nextLine();
		System.out.println("Enter Password:");
		String Password = input.nextLine();
		for(User user : Users){
			if(user.getUserName().equals(UserName) && user.getPassword().equals(Password)){
				System.out.println("Login Successful!");
				return;//in final code will advance to user dashboard
				
			}
			else{
				System.out.println("Incorrect Username or Password.");
				System.out.println("Would you like to reset your password? (yes/no)");
				//branches to password change or loops based on input
				if(input.nextLine().toUpperCase().equals("YES")){
					ResetPassword(input, user);
				}
				else if(input.nextLine().toUpperCase().equals("NO")){
					continue;
				}
			}
		}
		}
	}
	//Resets password after verifying user with security question
	public static void ResetPassword(Scanner input, User user){
		while(true){
			System.out.println(user.getVerificationQuestion());
			String answer = input.nextLine();
			if(answer.equals(user.getVerificationAnswer())){
				System.out.println("Enter New Password:");
				String newPassword = input.nextLine();
				user.setPassword(newPassword);

			}
			else{
				System.out.println("Would you like to try again? (yes/no)");
				if(input.nextLine().toUpperCase().equals("YES")){
					continue;
				}
				else if(input.nextLine().toUpperCase().equals("NO")){
					return;
				}
			}
		}
	}
	//Deletes account from arraylist
	public static void DeleteAccount(Scanner input, ArrayList<User> Users){
		System.out.println("Enter username of account to delete:");
		String TargetUserName = input.nextLine();
		for(User user: Users){
			if(user.getUserName().toUpperCase().equals(TargetUserName.toUpperCase())){
				Users.remove(user);
				System.out.printf("Account %s Deleted Successfully!\n", TargetUserName);
				return;
			}
		}
	}
	//Function to save users from arraylist to file
	public static void SaveUsersToFile(ArrayList<User> Users) throws IOException{
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
		for(User user : Users){
			writer.write(user.getUserName() + "," + user.getPassword() + "," + user.getVerificationQuestion() + "," + user.getVerificationAnswer());//add another comma if more fields are added
			writer.newLine();
		}
		writer.close();
	}
	//Function to load user from file to ArrayList
	public static void LoadUserFromFile(ArrayList<User> Users) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
		String line;
		while((line = reader.readLine()) != null){
			String[] parts = line.split(",");
			User user = new User(parts[0], parts[1], parts[2], parts[3]);//add another part if more fields are added
			Users.add(user);
		}
		reader.close();
	}
	//Main method responsible for initializing the list of users and scanner for input
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ArrayList<User> Users = new ArrayList<User>();
		LoadUserFromFile(Users);
		Scanner input = new Scanner(System.in);
		MainMenu(input, Users);
	}

}

