package main.se450.observable;

/*
 * Name     : 
 * Depaul#  : 
 * Class    : SE 450
 * Project  : Final
 * Due Date : xx/xx/2017
 *
 * class Motion
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import main.se450.factories.NamedThreadFactory;
import main.se450.gui.ShapeDisplay;
import main.se450.interfaces.IMotionObservable;
import main.se450.interfaces.IObservable;
import main.se450.model.Configuration;
import main.se450.singletons.ConfigurationManager;

public class Motion extends AbstractObserver implements Runnable, IMotionObservable, IObservable
{
	private static Motion motion = new Motion(); 
	
	private boolean inMotion = false;
	private ScheduledThreadPoolExecutor scheduler = null;

	private final static int FRAMES_PER_SECOND = 30; 
	
	private final static int NANO_SECONDS_PER_SECOND = 1000000000;
			
	private ArrayList<IMotionObservable> observables  = new ArrayList<IMotionObservable>();
	
	private Motion()
	{
		startObserver(this);
	}

	private static Motion getMotion()
	{
		return motion;
	}
		
	public static void startObserving(final IMotionObservable imo)
	{
		Motion motion = getMotion();
		if (motion != null)
			motion.addObserver(imo);
	}
	
	private void addObserver(final IMotionObservable imo)
	{
		if (imo != null)
		{
			if (!isObserving(imo))
			{
				observables.add(imo);
		
				if (!getIsInMotion())
					startMotion();
			}
		}
	}
	
	public static void stopObserving(final IObservable iObservable)
	{
		Motion motion = getMotion();
		if (motion != null)
			motion.removeObserver(iObservable);
	}

	private void removeObserver(final IObservable iObservable)
	{
		observables.remove(iObservable);

		if (observables.isEmpty())
			stopMotion();
	}
	
	public static void toggleObserving()
	{
		Motion motion = getMotion();
		if (motion != null)
			motion.toggleMotion();
	}
	
	private void toggleMotion()
	{
		if (getIsInMotion())
		{
			stopMotion();
		}
		else
		{
			startMotion();
		}
	}
	
	private boolean isObserving(final IMotionObservable iObservable)
	{
		boolean bIsObserving = false;
		
		if (iObservable != null)
		{
			bIsObserving = observables.contains(iObservable);
		}
		
		return bIsObserving;
	}
	
	private synchronized void startMotion()
	{
		ConfigurationManager cm = ConfigurationManager.getConfigManager();
		if (cm!= null){
			Configuration c = cm.getConfiguration();
			if(c!=null){
				setIsInMotion(true);
				
				int nfps = c.getframespersecond();
				if(nfps>0){
					if(scheduler == null){
						
					}
				}
			}
		}
		
		setIsInMotion(true);
		
		if (FRAMES_PER_SECOND > 0)
		{
			if (scheduler == null)
			{
				NamedThreadFactory motionThread = new NamedThreadFactory("Motion");
				
				scheduler = new ScheduledThreadPoolExecutor(1, motionThread);
				if (scheduler != null)
					scheduler.scheduleAtFixedRate(this, 0, NANO_SECONDS_PER_SECOND / FRAMES_PER_SECOND, TimeUnit.NANOSECONDS);//~20 frames per second
			}
		}
	}

	private void stopMotion()
	{
		setIsInMotion(false);
		
		if (scheduler != null)
		{
			scheduler.shutdown();
			
			scheduler = null;
		}
	}
	
	public static final boolean isInMotion()
	{
		Motion motion = getMotion();
		return (motion != null ? motion.getIsInMotion() : false);
	}

	public final boolean getIsInMotion()
	{
		return inMotion;
	}
	
	private final void setIsInMotion(final boolean bIsInMotion)
	{
		inMotion = bIsInMotion;
	}
	
	public void run()
	{
		if ((observables != null) && (isInMotion()))
		{
			Iterator<IMotionObservable> iiObservables = observables.iterator();
			while (iiObservables.hasNext())
			{
				IMotionObservable iObservable = iiObservables.next();
				if (iObservable != null)
				{
					iObservable.update();
				}
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
