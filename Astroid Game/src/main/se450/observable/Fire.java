package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IFireObservable;
import main.se450.interfaces.IObservable;

public class Fire extends AbstractObserver implements IObservable {
	private static Fire fire = new Fire();
	private ArrayList <IFireObservable> fireobservable = new <IFireObservable>ArrayList();
	
	private Fire()
	{
		startObserver(this);
	}
	
	public static void startObserving(final IFireObservable iFireObservables){
	}
	
	public static void stopObserving(final IFireObservable iFireObservables){
	}
	
	public static void fire()
	{
		fire.observed();
	}
	
	private synchronized void addObserver(final IFireObservable iFireObservables){
		if (iFireObservables!=null){
			if(!fireobservable.contains(iFireObservables)){
				fireobservable.add(iFireObservables);
			}
		}
	}
	
	public synchronized void removeObserver(final IFireObservable iFireObservables){
		
				fireobservable.remove(iFireObservables);	
	}
	
	public synchronized void update(){
		
		if (fireobservable!=null){
			Iterator<IFireObservable> ifo = fireobservable.iterator();
			while(ifo.hasNext())
			{
				IFireObservable iifo = ifo.next();
				if(ifo !=null){
					iifo.fire();
				}
						
			}
		}
	}
}
