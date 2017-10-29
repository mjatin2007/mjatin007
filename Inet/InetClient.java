/*--------------------------------------------------------

1. Name / Date: Jatin Mannepalli, 1/11/2017

2. Java version used, if not the official version for the class: Java 1.8


3. Precise command-line compilation examples / instructions:

e.g.:

> javac InetClient.java


4. Precise examples / instructions to run this program:

e.g.:

In separate shell windows:

> java InetClient
> java InetServer

All acceptable commands are displayed on the various consoles.

This runs across machines, in which case you have to pass the IP address of
the server to the clients. For exmaple, if the server is running at
140.192.1.22 then you would type:

> java InetClient 140.192.1.22

5. List of files needed for running the program.

e.g.:

  a. Worker.class
 b. InetServer.class
 c. InetClient.class

5. Notes:

e.g.:

Tried to understand the program as thoroughly as I could and commented them accordingly. Changed the server name as well to understand.
Comparitive to that of InetServer.java this program will smoothly and cause no trouble and wont hand much. 

----------------------------------------------------------*/


import java.io.*; 
// Library to provide input and output for streams of data and Serialization
import java.net.*; 
// Library to fetch classes that implement networking applications


public class InetClient{
 public static void main (String args[]) {
 String seName; //server Name of type String initialized
 if (args.length < 1) seName = "localhost"; //if server name not provided during running it would be taken localhost as a default name.
 else seName = args[0];

 System.out.println("Clark Elliott's Inet Client, 1.8.\n"); //printing name of the Inet Client.
 System.out.println("Using server: " + seName + ", Port: 1565"); //printing out seName
 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
 
 try {
 String name;
 do {
 System.out.print
 ("Enter a hostname or an IP address, (quit) to end: ");//expecting domain name from user unless he/she types quit
 System.out.flush ();
 name = in.readLine ();
 if (name.indexOf("quit") < 0)
 getRemoteAddress(name, seName);
 } while (name.indexOf("quit") < 0); // if quit is the input from the user the loop ends out cancellation output is returned
 System.out.println ("Cancelled by user request.");
 } catch (IOException x) {x.printStackTrace ();}
 }

 static String toText (byte ip[]) {
 StringBuffer result = new StringBuffer ();
 for (int i = 0; i < ip.length; ++ i) {
 if (i > 0) result.append (".");
 result.append (0xff & ip[i]);
 }
 return result.toString ();
 }
//returing Ip address for the domain name server entered by the client
 static void getRemoteAddress (String name, String seName){
 Socket sock;
 BufferedReader fromServer;
 PrintStream toServer;
 String textFromServer;

 try{
 /* Connection will be open with proper seName and the port number 1565 */
 sock = new Socket(seName, 1565);

 // Creates I/O streams for the socket created:
 fromServer =
 new BufferedReader(new InputStreamReader(sock.getInputStream()));
 toServer = new PrintStream(sock.getOutputStream());
 
 toServer.println(name); toServer.flush(); // This will be Sending machine name or IP address to server

 /*This will Read three lines of response from the server while the other block will be waiting synchronously. Synchronous calls are better when handling 
multi-threading. They are usually the calls which one has to wait till it gets over so as to start the next one. */

 for (int i = 1; i <=3; i++){
 textFromServer = fromServer.readLine();
 if (textFromServer != null) System.out.println(textFromServer);
 }
 sock.close();
 } catch (IOException x) { //Exception handling is done over here. The I/O exception will be in place if the try statement fails.
 System.out.println ("Error in the Socket.");
 x.printStackTrace ();
 }
 }
}