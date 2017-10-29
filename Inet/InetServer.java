/*--------------------------------------------------------

1. Name / Date: Jatin Mannepalli 1/11/2017

2. Java version used, if not the official version for the class: Java 1.8


3. Precise command-line compilation examples / instructions:


> javac InetServer.java
> javac InetClient.java


4. Precise examples / instructions to run this program:


In separate shell windows:

> java InetServer
> java InetClient

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

If you notice the server hanging, please shut it down and run the program again. It shouldnt be creating any trouble.
I have tried on increaing the Queue Length to 8, and my system got over heated so changed it back to 6 and works well now.
The client shell doesnt hand and will work smoothly comaparitively compared with server shell.

----------------------------------------------------------*/


import java.io.*;
// Library to provide input and output streams of data and Serialization
import java.net.*; 
// Library to fetch classes that implement networking applications

/** 
The Workers role over here is to send the output by first accepting input from client.
Worker Class definition inheriting from Thread Class
*/
class Worker extends Thread { 
 
 Socket sock;// Socket, s initialized
 /**
 Constructor s has been declared here which is for socket
 */
 Worker (Socket s) 
 {sock = s;} 
 
 public void run(){
	 //Here the server will start giving and assigning tasks and will start working
 // Get Input streams with BufferedReader and output Streams with PrintStream from the socket
 PrintStream out = null;
 BufferedReader in = null;
 try {
 in = new BufferedReader
 (new InputStreamReader(sock.getInputStream()));
 out = new PrintStream(sock.getOutputStream());

 try {
 String name;
 name = in.readLine ();//expecting input from the client, here in this case "The Hostname"
 System.out.println("Looking up " + name);
 printRemoteAddress(name, out);//The Ip address of the Hostname and the Hostname to be printed out here..
 } catch (IOException x) {
 System.out.println("Server read error");//Exception handling if there is an input from the user/cleint
 x.printStackTrace ();
 }
 sock.close(); // if a socket has been opened, it has to be closed by the end of the program. Please make sure not to close the server here.
 } catch (IOException ioe) {System.out.println(ioe);}
 }

 static void printRemoteAddress (String name, PrintStream out) {
 try {
 out.println("Looking up " + name + "...");
 InetAddress machine = InetAddress.getByName (name);
 out.println("Host name : " + machine.getHostName ());
 out.println("Host IP : " + toText (machine.getAddress ()));
 } catch(UnknownHostException ex) {
 out.println ("Failed in atempt to look up " + name); //Error handling again being done in case of failure
 }
 }

 
 static String toText (byte ip[]) { 
 StringBuffer result = new StringBuffer ();
 for (int i = 0; i < ip.length; ++ i) {
 if (i > 0) result.append (".");
 result.append (0xff & ip[i]);
 }
 return result.toString ();
 }
}
public class InetServer {

 public static void main(String a[]) throws IOException {
 int q_len = 6; // Allowed number of requests in queue
 int port = 1565; //port number defined
 Socket sock;

 ServerSocket servsock = new ServerSocket(port, q_len);

 System.out.println
 ("Clark Elliott's Inet server 1.8 starting up, listening at port 1565.\n");
 while (true) {
 sock = servsock.accept(); // waiting....
 new Worker(sock).start(); // Create new worker to handle the new/next client connection.
 }
 }
}