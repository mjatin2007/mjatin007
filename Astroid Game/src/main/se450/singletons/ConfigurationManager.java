package main.se450.singletons;

import java.awt.Color;
import java.awt.Graphics;
import main.se450.interfaces.IStrategy;
import main.se450.model.Configuration;

public class ConfigurationManager
{
	private static ConfigurationManager configManager = null;
	
	private Configuration configuration = null; 
	
	private int width  = 0;
	private int height = 0;
	
	private Graphics graphics = null;
	
	static
	{
		configManager = new ConfigurationManager();
	}
	
    private ConfigurationManager()
    {
    }
    
	public final static ConfigurationManager getConfigManager() 
	{
		return configManager;
	}
		
	public void setDisplaySize(int nWidth, int nHeight)
	{
		width  = nWidth;
		height = nHeight;
	}
	
	public void setGraphics(Graphics oGraphics)
	{
		graphics = oGraphics;
	}
	
	public Graphics getGraphics()
	{
		return graphics;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}

	public void setConfiguration(Integer fps, int sw, int sh, Float ss, Float ft, Float rt,
			Float f, Float lr, Color c, IStrategy b) {
		// TODO Auto-generated method stub
		configuration = new Configuration(fps, sw, sh, ss, ft, rt, f, lr, c, b ); 
	}
	
	public synchronized final Configuration getConfiguration(){
		return configuration;
	}
}
      