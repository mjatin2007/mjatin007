import java.net.*;
import java.io.*;
import java.util.*;

public class JokeServer {
public static void main(String[] args) { 
try {
 ServerSocket sock = new ServerSocket(6013);

 // 5 jokes stored in an array of strings
 String[] jokes = new String[5];
 jokes[0] = "Tech: Make sure all windows are closed; Customer:"
	 + " Im in the basement. I don't have any windows here";
 jokes[1] = "Microsoft gives you Windows... Linux gives you the whole house";
 jokes[2] = "Computers are like air conditioners -- they stop working properly if you open WINDOWS ";
 jokes[3] = "Beware of programmers that carry screwdrivers";
 jokes[4] = "All computers run at the same speed... with the power off";

 // Random number
 Random generator = new Random();
 int randomNumber;

 //now listen for connections 
 while (true) {
 randomNumber = generator.nextInt(5);
 Socket client = sock.accept(); 
 PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
 //write theJoketothesocket 
 pout.println(jokes[randomNumber]);
 //close the socket and resume 
 //listening for connections 
 client.close();
 }
} 
 catch (IOException ioe) {
 System.err.println(ioe);
 }
}
}