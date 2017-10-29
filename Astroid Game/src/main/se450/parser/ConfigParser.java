package main.se450.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.se450.exceptions.BadStrategyException;
import main.se450.factories.StrategyFactory;
import main.se450.interfaces.IStrategy;
import main.se450.singletons.ConfigurationManager;
import main.se450.utilities.Extractor;

/**
*
*  The parser for the shapes file
* 
* @author Anthony Freund
*
*/

public class ConfigParser
{
	/**
	*
	* Parses the shape file
	* 
	* @author Anthony Freund
	* 
	* @param the file to parse
	*
	* @return array of shapes
	*/

	public static boolean loadConfiguration(String fileName)
	{
        boolean loadconfig = false;
        
		try
		{ 
	        JSONParser parser = new JSONParser();
	 
	        Object obj = parser.parse(new FileReader(fileName));
	 
	        JSONObject jsonObject = (JSONObject) obj;
	 
	        JSONArray jsonArray = (JSONArray) jsonObject.get("game");
	        
	        Iterator<?> jsonIterator = jsonArray.iterator();
	        while (jsonIterator.hasNext())
	        {
	        	JSONObject c = (JSONObject)jsonIterator.next();
	        	if (c.containsKey("framespersecond"))
	        	{
	        		try{
	        		
	        		IStrategy is = StrategyFactory.makeStrategy(c.get("borders").toString());
	        		ConfigurationManager.getConfigManager().setConfiguration(Extractor.extractInteger(c.get("framespersecond")),
	        																 Extractor.extractInteger(c.get("shipwidth")),
	        																 Extractor.extractInteger(c.get("shipheight")),
	        																 Extractor.extractFloat(c.get("shotspeed")),
	        																		Extractor.extractFloat(c.get("forwardthrust")),
	        																		Extractor.extractFloat(c.get("reversethrust")),
	        																		Extractor.extractFloat(c.get("friction")),
	        																		Extractor.extractFloat(c.get("leftright")),
	        																		Extractor.extractColor(c.get("color")),
	        																		is);
	        		loadconfig = true;
	        		}		
	        		catch(BadStrategyException bs)
			        {
			        }
	        	}
	        }
	        
		}
		catch(FileNotFoundException eFileNotFound)
        {
        }
        catch(IOException eIOException)
        {
        	
        }
		catch(ParseException eParseException )
        {
        }
		return loadconfig;        	

	}
}