//Name: Jatin Mannepalli
//Java Version: 1.8

// Precise Instructions to compile/run the program
// >javac MyWebServer.java
// >java MyWebServer

//In order to run the program first you need to download the XStream.jar files provided
//else the compiler would give error
//Three new classes have been added to mywebserver and they are BCWorker, myDataArray and BCListener.
//This program has been edited as per Professor elliot's guidance

import java.io.*; 
// Library to provide input and output for streams of data and Serialization
import java.net.*;
// Library to fetch classes that implement networking applications
import java.util.*;
//library to fetch utility files.
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

class myDataArray {
  int num_lines = 0;//mydatarray is here to store the contents in the file
  String[] lines = new String[10];
}

class BCWorker extends Thread {
    private Socket sock;
    private int i;
    BCWorker (Socket s){sock = s;}
    PrintStream out = null; BufferedReader in = null;

    String[] xmlLines = new String[15];
    String[] testLines = new String[10];
    String xml;
    String temp;
    XStream xstream = new XStream();
    final String newLine = System.getProperty("line.separator");
    myDataArray da = new myDataArray();
    
    public void run(){
      System.out.println("Called BC worker.");
      try{
	in =  new BufferedReader(new InputStreamReader(sock.getInputStream()));
	out = new PrintStream(sock.getOutputStream()); // to send ack back to client
	i = 0; xml = "";
	while(true){
	  temp = in.readLine();
	  if (temp.indexOf("end_of_xml") > -1) break;
	  else xml = xml + temp + newLine; // Should use StringBuilder in 1.5
	}
	System.out.println("The XML marshaled data:");
	System.out.println(xml);
	out.println("Acknowledging Back Channel Data Receipt"); // send the ack
	out.flush(); sock.close();
	
        da = (myDataArray) xstream.fromXML(xml); // deserialize / unmarshal data
	System.out.println("Here is the restored data: ");
	for(i = 0; i < da.num_lines; i++){
	  System.out.println(da.lines[i]);
	}
      }catch (IOException ioe){
      } // end run
    }
}

class BCListener implements Runnable {
  public static boolean adminControlSwitch = true;
  
  public void run(){ // the admin loop will run
    System.out.println("In BC Looper thread, waiting for 2570 connections");//standard print statement
    
    int q_len = 6; //length of the queue initalized
    int port = 2570;  //back channel port number initalized
    Socket sock;
    
    try{
      ServerSocket servsock = new ServerSocket(port, q_len);
      while (adminControlSwitch) {//next admin client connection
	sock = servsock.accept();
	new BCWorker (sock).start(); 
      }
    }catch (IOException ioe) {System.out.println(ioe);}
  }
}

class Worker extends Thread
{
	Socket soc;// socket initialized

	Worker(Socket s)//worker thread
	{
		soc = s; //socket initialized
	}

	
	public void run()
	{
		PrintStream out = null;//Print stream given the flexibility to print various types of data conveniently.
		BufferedReader in = null;//Buffered reader helps in reading the datatypes such as arrays, strings or characters efficiently.
		String contentType = null;//content type initialized for MIME type overe here
		String getFile = null;//getfile initialized to store thr http requests recived from the browser.

		try {
			//In initialized for stream of input
			in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			//out initialized for stream of output from PrintStream			
			out = new PrintStream(soc.getOutputStream()); 
			
			
			if (MyWebServer.modeSwitch = false) //when the variable is false
			{
				System.out.println("Shutting down My Web Server");//notify on the console that webserver is shutting down.
			}
			else 
			{
				String storeHTTP = null; //string initialized to store HTTP request sent by browser.

				try { 
					storeHTTP = in.readLine(); //this will start reading the incoming browser request

					System.out.println(storeHTTP);//print out the browser request on the server
					
					getFile = parseFile(storeHTTP); //parse/read the name of the file from the http request recieved
					

					//when the browser is sending out the http requests keep reading the requests and print them.
					while(in.ready())
					{
						//storing the HTTP request sent from the server one line at a time
						storeHTTP = in.readLine();
						//print out the http requests recieved 
						System.out.println(storeHTTP);
					}


					//if the name of the file contains a "." send the directory view on to the browser.
					if(!getFile.contains("."))
					{
						directoryView(out, getFile); //send a directory view of folder to browser
					}
					else
					{
			
						//On the basis of the type of file extension generate reponse.
						if(!getContenttype(getFile).equals("application/octet-stream"))
						{
							responseHTTP(getFile, out, soc); //normal reponse expected and generated
						}
						// This If statement below will execute if the http request recived is for the fake-cgi.
						else if(getFile.contains("fake-cgi"))
						{
							
							// this is to make sure if any form was submitted. In our case it is addnum file.
							// The ? resembles weather any kind of resources were requested for.
							if(getFile.contains("?"))
							{
								String[] readURL = getFile.split("?"); //store the file in string Read URL and split it with ?
								//The URL split will be sent to process form method to handle fake-cgi requests 
								cgiHandler(out, readURL[0], readURL[1]); 
								//print out the urls that have been recieved
								System.out.println("URL 1: " + readURL[0] + " URL 2: " + readURL[1]);
							}
							else
								//if there wasnt any ? in the cgi script run normally
								cgiHandler(out, getFile, ""); 
						}
						else
							//if not found send the 404 message to the broswer
							notFound404(out); 
					}


				}
				catch (IOException x) //IO Exception handling
				{
					//These methods are usually thrown whenever the input/output operation fails to interpret
					System.out.println("Error");
					x.printStackTrace();//This method prints throwable and ts backtrac into the error stream.
				}

			}
			soc.close(); //Every socket that has been created needs to be closed
		}
		catch (IOException ioe) //IO Exception handling
		{ 
		System.out.println(ioe); //Print the IO Exception if any.
		} 
	}

	//read the first http request recived and check for its start position and end position.
	static String parseFile(String count)
	{
		int countBeg = count.indexOf("/"); // render the beginning position of the file/http request
		int countEnd = count.indexOf(" ", countBeg); //render the end psotion of the file/http/folder request.
		System.out.println(count.substring(countBeg+1, countEnd));//Will print the substring between beining and the end.
		String fileForm = "";//string fileForm intialized
		String pathURL = count.substring(countBeg+1, countEnd); //render the file name with begining and end subtsring
		int endOfFile = pathURL.lastIndexOf("/", countEnd); //render the last occurence of "/" in the file/folder request.
		int questionMark;//questionMark initialized to check if any ? in the request

		
		// This if statement will run if the position of ? is larget than /.
		// This means to check if any data is being passes from one file to another.
		if((questionMark = pathURL.lastIndexOf("?", countEnd)) > endOfFile)
		{
			
			//render the reqest which is present after the ?.
			fileForm = pathURL.substring(questionMark);
			System.out.println(fileForm);// the the final request/file name
		}	
		
		//render file name for path and add the file form with it
		String nAme = count.substring(countBeg+1, countEnd) + fileForm;

		//check if the file name has / in the end and remove it if present.
		if(nAme.lastIndexOf("/") == nAme.length())//check with the length of the nAme.
			nAme = nAme.substring(0, nAme.length()-1);//discard the '/' here
		
		//nAme = (nAme.lastIndexOf("/") == nAme.length()) ? nAme.substring(0, nAme.length()-1) : nAme;

		System.out.println("Name of file:" + nAme);//print out the name of the file.
		return nAme;

	}

	//Guess the type of MIME and send the file contents.
	static String getContenttype(String pat)
	{
		//if else-if statements for various types of file extensions/MIME.
		if(pat.endsWith(".htm") || pat.endsWith(".html"))
        {
            return "html";//html formal
        }
		else if(pat.endsWith(".gif"))
        {
            return "image/gif";//gif image format
        }
		else if(pat.endsWith(".png"))
        {
            return "image/png";//gif image format
        }
		else if(pat.endsWith(".txt"))
        {
            return "txt";//notepad-txt format
        }
		else if(pat.endsWith(".java")|| pat.endsWith(".class"))
        {
            return "java/class";//java files
        }
		else if(pat.endsWith(".jpg") || pat.endsWith(".jpeg")
                || pat.endsWith(".jpe"))
        {
            return "image/jpeg";//jpeg file format.
        }
		else if(pat.endsWith(".pdf"))
        {
            return "application/pdf";//pdf file format.
        
		}
		else if(pat.endsWith(".mp4"))
        {
            return "video-format";//pdf file format.
        
		}
		else if(pat.endsWith(".xyz"))
        {
            return "application/xyz";//pdf file format.
        
		}
		else if(pat.endsWith(".doc")|| pat.endsWith(".docx"))
        {
            return "word-document";//pdf file format.
        
		}
		else
        {
            return "application/octet-stream";//if any othr mime
        }
		
	}

	//this is to render normal http responses
	static void responseHTTP(String pat, PrintStream out, Socket soc)
	{
		//Here I am using string builder instead of string as string builders usually occupies less memeory
		//Stringbuilders are faster in nature as well.
		//initializing http body
		StringBuilder httpBody = new StringBuilder(); 
		//initializing http header
		StringBuilder httpHeader = new StringBuilder(); 
		//render the type of mime below
		String contentType = getContenttype(pat);
		//initialize the file with path.
		File fileType = new File(pat);

		//check if the requested file exists in the directory.
		//if the file exists it will enter the if loop below
		if(fileType.exists())//if the file exists.
		{
			BufferedReader readFile  = null; //The variable has been initailized so as to read the contents of the file.
			String streamRead = null; //stream read initialized.
			long contentLength = 0;//length of the header initialized to zero initially
			FileInputStream readImage = null; //The variable has been initailized so as to read the contents of the file in case of image
			
			try
			{
				//it the if the mime type is jpeg or gif or png
				if(contentType.contains("image/jpeg") || contentType.contains("image/gif") || contentType.contains("image/png")||contentType.contains("video-format"))
				{
					//render the stream for image
					readImage = new FileInputStream(fileType);
					//render the length of the file for displaying header
					contentLength = fileType.length(); 
					//buffer has been initialized with a size of 50000 bytes to hold image bytes.
					byte[] imageBuffer = new byte[50000];
					int lengthJGP = 0;//length of the image initialized to zero

					//Generate HTTP headers
					httpHeader.append("HTTP/1.1 200 OK\n");//version of http and 200 is the code to imply that request was successful.
					httpHeader.append("Content-Type: " + contentType + "\n");//type of mime as header
					httpHeader.append("Content-Length: " + contentLength + "\n");//length of the file and content.
					httpHeader.append("Accept-Ranges: bytes\n");//range of bytes accepted
					httpHeader.append("Datatype\n");//type of data.
					

					//the header above needs to be sent to the browser
					// but inorder to do that they must be first converted to bytes and then sent 
					byte[] byteConvert = httpHeader.toString().getBytes();
					int lengthofBytes = byteConvert.length;//store the length of bytes in lengthofBytes
					out.write(byteConvert);//this will write the least significant bit
							
					
					while((lengthJGP = readImage.read(imageBuffer)) != -1)//read the bytes of the image
					{
						out.write(imageBuffer, 0, lengthJGP);//send them across the browser.
					}
					out.flush(); //flush out the buffer
					
					

				}
				
				else //it will execute if the file is not image or media file.
				{
					//readfile to read the contents of the file.
					readFile = new BufferedReader( new InputStreamReader( new FileInputStream(pat)));

					//read the non media text file
					while((streamRead = readFile.readLine()) != null)//streamRead will check as long as there is a file to read.
					{
						httpBody.append(streamRead + "\n");//now append it with http body response.
					}
					contentLength = httpBody.toString().getBytes().length;// render the length of the hhtp body reponse for header. 

					//create normal http response
					httpHeader.append("HTTP/1.1 200 OK\r\n");//version of http and 200 is the code to imply that request was successful.
					httpHeader.append("Content-Length: " + contentLength + "\r\n");//length of the file
					httpHeader.append("Content-Type: " + contentType + "\r\n");//type of mime in the header
					httpHeader.append("Accept-Ranges: bytes\n");//range of bytes accepted
					httpHeader.append("Datatype:"+ contentType + "\r\n");//type of data.
					
	
					out.println(httpHeader.toString() + httpBody.toString());//print this out at the browser's end.
					System.out.println(httpHeader.toString() + httpBody.toString()+ "\n");//print this on the console's end.
					System.out.println("HTTP Status and Code: " + httpHeader.toString() + httpBody.toString()+ "\n");//print this on the console's end.
				}
			}
			catch (IOException ioe) { ioe.printStackTrace(); }//catch io exception here.
			//exception handling
		}
		else
			notFound404(out); //if resource not found print out the 404 error message

	}

	//this method is to view the folders of file type in diretory view.
	static void directoryView(PrintStream out, String folderFile)
	{
		//Here I am using string builder instead of string as string builders usually occupies less memeory
		//Stringbuilders are faster in nature as well.
		//initializing http body
		StringBuilder httpBody = new StringBuilder();
		//initializing http body
		StringBuilder httpHeader = new StringBuilder();


		File fileType = new File(".."); //fileTtpe has been initailized for the current directory
		String rootDir = null;//root directoy initialized.
		
		if (folderFile.equals(" "))//check if the folder is empty
		{

			//I tried to go back to the root directory here. I believe there are few glitches here.
			fileType = new File("..");
			rootDir = "/";//roor directory
		}
		else // if the folder is not empty show the contents of the folder.
		{
			fileType = new File("./" + folderFile); //generate the files for the requested diretory/folder
			// put '/' in the front to dirfferentiate the file
			folderFile = "/" + folderFile;  
			// render the root of the requested directory.
			rootDir = folderFile.substring(0, folderFile.lastIndexOf("/"));

		}

		File[] directoryFiles = fileType.listFiles(); //list file of dir
		String nAme = null;
		
		//the http body for the directory
		httpBody.append("<h1> Welcome to Jatin's WebServer </h1>");
		httpBody.append("<h2> Contents of " + folderFile + "</h2>");
		httpBody.append("<pre>");
		httpBody.append("<a href='" + rootDir + "'>Root Directory</a><br><br>");

		//for all the directories and files in requested folder
		for( int i=0; i<directoryFiles.length; i++)//traverse the directory
		{
			//if the selcted is a directory
			if (directoryFiles[i].isDirectory())
			{
				//append to http body the dir and its path
				httpBody.append("[Directory]&nbsp; <a href=" + directoryFiles[i].toString().substring(1) + ">" + directoryFiles[i].toString().substring(2) + "</a>\n");
				System.out.println("dir: " + directoryFiles[i]);//print out the directory
			}
			// if not a directory consider it a file/txt/image
			else
			{
				httpBody.append("[File/TXT/Image] <a href=" + directoryFiles[i].toString().substring(1) + ">" + directoryFiles[i].toString().substring(2) + "</a>\n");
				System.out.println("file/txt/image: " + directoryFiles[i]);//print out the files
			}
		}

		httpBody.append("</pre>");//pre formatted text
		String bodyString = httpBody.toString();//initialize the bodystring
		//render the length of the body for hearder purpose.
		int contentLength = bodyString.getBytes().length;

		//http reponses
		httpHeader.append("HTTP/1.1 200 OK\r\n");//version of http and 200 is the code to imply that request was successful.
		httpHeader.append("Content-Length: " + contentLength + "\r\n");//length of file
		httpHeader.append("Content-Type: text \r\n");//type of mime
		httpHeader.append("Datatype: \r\n");//type of data.
		//print out the string body
		System.out.println(bodyString);
		//browser is being sent the http reponses.
		out.println(httpHeader.toString());
		//brower is being sent the bodystring
		out.println(bodyString);
			
	}

	//method for handling the fake-cgi scripts
	// This method will also handle the files with no mime extensions as well.
	static void cgiHandler(PrintStream out, String pat, String content)
	{
		System.out.println("path: " + pat);//print out the path
		String noMime = pat.split("[.]")[0]; //render the filename without mime extension
		String[] runCGI = null; //to run the fake-cgi script

		String getFile = null;

		//if the file has a cgi ext.
		//cgi is used as file ext for purposed of this assignment only
		if(pat.split("[.]")[1].equals("fake-cgi"))// if it is a cgi-request
		{
			
			getFile = noMime + ".class";//place .class at the end of the file.
			//run cgi subproesses
			runCGI = new String[]{"fake-cgi/",  noMime.split("/")[1], content}; //fake-cgi script
		}
		

		File newFile = new File(getFile);//newfile

		
		if(newFile.exists())// if the script is present
		{
			try//try statement
			{
				//RunTime helps us in interface with the environment on which the program is running
				//the getRuntime helps in returning the object on which the proggram is currently working on.
				// a process has been initialized at the runtime.
				Process pRuntime = Runtime.getRuntime().exec(runCGI); 
				//the input stream below will help to get input from the process
				BufferedReader in = new BufferedReader( new InputStreamReader( pRuntime.getInputStream()));

				String streamRead = null;//streamRead initialized		

				//parse the data coming from the processes through the output
				while ((streamRead = in.readLine()) != null)
				{
					//as long as the above statement is valid send the data to the browser.
					out.println(streamRead);
				}

				out.flush();//flush out the stream
				System.out.flush();//flush out the stream
			}
			catch (IOException ioe) 
			{ 
			ioe.printStackTrace(); //io exception handling 
			}
			catch (Exception e) 
			{ 
			e.printStackTrace(); //io exception handling 
			}
		}
	}

	//if the file is not present this method will be invoked. Error 404
	static void notFound404(PrintStream out)
	{
		StringBuilder httpHeader = new StringBuilder();//httpHeader initialized
		StringBuilder httpBody = new StringBuilder();
		
		try
		{

			//the input stream below will help to get input from the process
			BufferedReader readFile = new BufferedReader( new InputStreamReader( new FileInputStream("notfound.html")));
			String streamRead = null;//streamRead initailized.
	
			int contentLength;// content length initialized for header
			while((streamRead = readFile.readLine()) != null)//parse the data coming from the processes through the output
			{
				httpBody.append(streamRead + "\n");//as long as the statement is true run this
			}

			contentLength = httpBody.toString().getBytes().length;//the length of content is the length of http body.
			httpHeader.append("HTTP/1.1 404 Not Found\r\n");//version of http and 404is the code to imply that resource is not available.
			httpHeader.append("Content-Length:" + contentLength + " \r\n");//length of the content
			httpHeader.append("Content-Type: text/html\r\n");// type of content
			
			System.out.println(httpHeader.toString() + httpBody.toString());
			out.println(httpHeader.toString() + httpBody.toString());//send the header and body to the browser in string form 
		}
		catch (IOException ioe) 
		{ 
		ioe.printStackTrace(); //IO Ecption handling
		}
	}
}
//main webserver function
public class MyWebServer
{
	public static boolean modeSwitch = true; //boolean statement on whether to run server is running or not
	
	public static void main(String args[]) throws IOException
	{
		//No. of requests limited for the operating system
		int q_len = 6;
		//port as suggested by Professor Eliott on which the server will be listening
		int port = 2540; 

		//socket initialized
		Socket soc;
		try{
	BCListener bc = new BCListener();//back channel listener
			Thread t = new Thread(bc);//thread initialized
			
			t.start();
		//socket initialized to accept the connections
		ServerSocket serverSocket = new ServerSocket(port, q_len); 
//standard output to show the program is up and running
		System.out.println("Jatins' Web Server is now listening to port " + port + "\n"); 

		//while server is still running
		while(modeSwitch)
		{
			//accpet a connection as soon as a request comes onto the socket
			soc = serverSocket.accept(); 
			BufferedReader reader =new BufferedReader(new InputStreamReader(soc.getInputStream()));
			//worker thread
			String req = "";
				String clientRequest = "";
				while ((clientRequest = reader.readLine()) != null) {
					if (req.equals("")) {
						req  = clientRequest;
					}
					if (clientRequest.equals("")) 
					{ 
						break;
					}
				}

				if (req != null && !req.equals("")) {
					new Worker(soc).start();
				}
			}
		}
		catch(IOException ex){
			//Handle the exception
			System.out.println(ex);
		}
		finally {
			System.out.println("Server has been shutdown!");
		}
	}
}