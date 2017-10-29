package main.se450.sound;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import main.se450.interfaces.ISound;

public abstract class Sound implements ISound, LineListener
{
	private String fileName = "";
    private Clip clip = null;
    private File file = null;
    
    private volatile boolean isPlaying = false;
    
	public Sound(String soundFile)
	{
		fileName = soundFile;
	    
        file = new File(fileName);

        try
	    {
	        clip = AudioSystem.getClip();
	        clip.addLineListener(this);	        
	        clip.open(AudioSystem.getAudioInputStream(file));
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
    }
	
	@Override
	public void finalize()
	{
		clip.close();
	}

	@Override
	public void play()
	{
		if (setPlaying(false, true))
		{
		    try
		    {
				clip.setMicrosecondPosition(0);
				clip.start();
		    }
		    catch (Exception exc)
		    {
		        exc.printStackTrace(System.out);
		    }
		}
	}
	
	@Override
	public void update(LineEvent myLineEvent) 
	{
		if (myLineEvent.getType() == LineEvent.Type.STOP)
		{
			setPlaying(true, false);
		}
    }
	
	private boolean setPlaying(boolean bCheck, boolean bIsPlaying)
	{
		boolean bSet = false;
		
		if (bCheck == isPlaying)
		{
			isPlaying = bIsPlaying;
			
			bSet = true;
		}
		
		return bSet;
	}
}
