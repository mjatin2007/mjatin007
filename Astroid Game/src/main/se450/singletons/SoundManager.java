package main.se450.singletons;

import java.util.HashMap;

import main.se450.constants.Constants;
import main.se450.interfaces.ISound;
import main.se450.sound.Fire;
import main.se450.sound.ForwardThrust;
import main.se450.sound.ReverseThrust;

public class SoundManager
{
	private static SoundManager soundManager = null;
	
	private HashMap<String,ISound> sounds = null;
	
	static
	{
		soundManager = new SoundManager();
	}
	
    private SoundManager()
    {
    	sounds = new HashMap<String,ISound>();
    	
    	sounds.put(Constants.FIRE,                   new Fire());
    	sounds.put(Constants.FORWARD_THRUST_PRESSED, new ForwardThrust());
    	sounds.put(Constants.REVERSE_THRUST_PRESSED, new ReverseThrust());
    }
    
	public final static SoundManager getSoundManager() 
	{
		return soundManager;
	}
	
	public void playFire()
	{
		sounds.get(Constants.FIRE).play();
	}
	
	public void playForwardThrust()
	{
		sounds.get(Constants.FORWARD_THRUST_PRESSED).play();
	}
	
	public void playReverseThrust()
	{
		sounds.get(Constants.REVERSE_THRUST_PRESSED).play();
	}
}
      