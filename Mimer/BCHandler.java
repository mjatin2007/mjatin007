
// This Handler program will open the specified file and will chenage the content to xml irrespective of the mime type 
// and send to the BC Port 2570
//Please make sure you have downloaded the XStream.jar files in the Professor Elliot's mimer page. else you might get errors.
//http://condor.depaul.edu/elliott/435/hw/programs/mimer/xstream-1.2.1.jar
//http://condor.depaul.edu/elliott/435/hw/programs/mimer/xpp3_min-1.1.3.4.O.jar
//also configure your shim.bat file for the program to running
import java.io.*;  //io librraies
import java.net.*;//network libraries 
import java.util.*;
import com.thoughtworks.xstream.XStream;//XStream libraries
import com.thoughtworks.xstream.io.xml.DomDriver;

//This handler has been created by combining the BCClient and Handler.java files.
//It will open the the files according to the file path given and will convert it into the desired xml format
// once it is converted it will send the content to the back channel of our mywebserver.

public class BCHandler
{
	
	private static final int BACK_PORT = 2570;//back channel port initialized to 2570
	private static String XMLfileName = "C:\\temp\\mimer.output";//file path for xml to be printed
  	private static PrintWriter outXML; //printing output of the xml file
  	private static File xmlFile;//XML File in where the xml will the printed
  	private static BufferedReader fromMimeDataFile;//This will contain the MIME read from the temporary file

  	public static void main (String args[]) {
  		
  		String serv_Name;//Name of the server initialized
		serv_Name = (args.length < 1) ? "localhost" : args[0];//If no arguement is passed the name of the server would be local host else it would be as
		
  		System.out.println("Starting up Jatin's BCHandler Application");//standard print to show the application is running

		System.out.println("Server name: " + serv_Name);//print the server name
		System.out.println("Port: 2570");//Print the port no. being used
		
		Properties jPorp = new Properties(System.getProperties());//object for the variable enviornment
		
		String lookFile = jPorp.getProperty("firstarg");//fetch the variable having the file name
		System.out.println("Name: " + lookFile);//print the name of the file
  
  		try {
  			
  			fromMimeDataFile = new BufferedReader(new FileReader(lookFile));//This will create a read, to read the data sent from the server
  			myDataArray mda = new myDataArray();//This will write all the mime data into the mda object
  			int i = 0;
  			// Reads the lines from the file (seven at most).
  			while(((mda.lines[i++] = fromMimeDataFile.readLine())!= null) && i < 10)//This while loop will read all the lines in the file
			//to the maximum of 10 lines
			{
  				System.out.println("Content: " + mda.lines[i-1]);//print out the content of the file
  			}
  			
  			mda.num_lines = i - 1;//length of buffer set
  			System.out.println("Lines: " + mda.num_lines);//print out the number of lines

  			
			XStream xstream = new XStream();//creating the xstream which will work with xml
			String xml = xstream.toXML(mda);//serializing the object into xml
			System.out.println("We have recieved the XML Output as:" + xml);//print out the xml
			 
			
			sendToBC(xml, serv_Name);// this will send the xml through the back we created
				
			xmlFile = new File(XMLfileName);//creating file object for the xml
			// check if xml file exists. if not fail.
			if (xmlFile.exists() == true && xmlFile.delete() == false)
			{
				throw new IOException("XML file delete failed.");//io exception for file failure
			}
			xmlFile = new File(XMLfileName);//creating file object for the xml
			
			if (xmlFile.createNewFile()== false)//check if the xml's creation was a success
			{
				throw (IOException) new IOException("XML file creation failed.");// throw ioexception for failure
			}
			outXML = new PrintWriter(new BufferedWriter(new FileWriter(XMLfileName)));//writer for the xml file
			outXML.println(xml);//print the xml into the file
  		} 
		catch (IOException x) 
		{
  			x.printStackTrace ();//IO Exception handling
  		}
  		finally {
			if (fromMimeDataFile != null) 
			{
				try {fromMimeDataFile.close();} //close the mime datafile
				catch (Exception ex) {}//catch the excpetion here
			}
			if (outXML != null) {
				try {outXML.close();} //close the xml output
				catch (Exception ex) {}
			}
  		}
  	}

  	/**
  	 * Send the XML data to the back channel.
  	 * @param sendData XML output data.
  	 * @param serverName server to connect back channel.
  	 */
  	static void sendToBC (String sendData, String serv_Name){
  		Socket sock = null;//socket initialized to null
  		BufferedReader fromServer = null;//Buffered reader helps in reading the datatypes such as arrays, strings or characters efficiently.
  		PrintStream toServer = null;//Print stream given the flexibility to print various types of data conveniently.
  		String textFromServer;
  		try {
  			
  			sock = new Socket(serv_Name, BACK_PORT);//This will open the connection of the back channel
  			toServer   = new PrintStream(sock.getOutputStream());//to server printstream
  			fromServer = new  BufferedReader(new InputStreamReader(sock.getInputStream()));//Bufferedreader from server
      
  			// Send the XML data to the server.
  			toServer.print(sendData);//send data to the server
  			toServer.println("end_of_xml");//end of xml to the server
  			toServer.flush(); //flush out the stream
  			//This will read lines response from the server and will be blocked
			//This will be waiting synchronously
  			System.out.println("Blocking on acknowledgment from Server... ");//Standard output to blocking acknowledgement
  			textFromServer = fromServer.readLine();//read from the server
  			if (textFromServer != null) {
  				System.out.println(textFromServer);//print out the text from server
  			}
  		} catch (IOException x) //IO Exception for socket error
		{
  			System.out.println ("Socket error.");//print out error
  			x.printStackTrace ();
  		}
		finally {
			if (fromServer != null) {
				try {fromServer.close();} //close the fromServer
				catch (IOException ex) {}//catch the IO exception
			}
			if (toServer != null) {
				toServer.close();//close the toServer
			}
			if (sock != null) {
				try {sock.close();}//close the socket
				catch (IOException ex) {}//IO exception
			}
		}
  	}
  
}