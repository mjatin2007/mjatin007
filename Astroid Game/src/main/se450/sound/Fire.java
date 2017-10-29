package main.se450.sound;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IFireObservable;
import main.se450.interfaces.IObservable;
import main.se450.observable.AbstractObserver;

public class Fire extends AbstractObserver implements IObservable
{
	
	/*public Fire()
	{
		super(".//sounds//fire.wav");
	}*/

	public static  Fire fire = new Fire();
	private ArrayList<IFireObservable> fireobservables= new ArrayList<IFireObservable>();
	
	public Fire() {
		// TODO Auto-generated method stub
		startObserver(this);
	}

	private void startObserver(Fire fire2) {
		// TODO Auto-generated method stub
		
	}

	public static void startObserving(final IFireObservable iFireObservable){
		fire.addObserver(iFireObservable);
	}
	
	public static void stopObserving(final IFireObservable iFireObservable){
		fire.removeObserver(iFireObservable);
	}
	
	private void removeObserver(IFireObservable iFireObservable) {
		// TODO Auto-generated method stub
		fireobservables.remove(iFireObservable);
	}

	public static void fire(){
		fire.observed();
	}

	private void observed() {
		// TODO Auto-generated method stub
		
	}

	private synchronized void addObserver (final IFireObservable iFireObservable){
		if(iFireObservable!=null){
			if(fireobservables.contains(iFireObservable)){
				fireobservables.add(iFireObservable);
			}
		}
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (fireobservables != null){
			Iterator<IFireObservable> ifo = fireobservables.iterator();
			while(ifo.hasNext()){
				IFireObservable  iFireObservable = ifo.next();
				if(ifo!=null){
					iFireObservable.fire();
				}
			}
		}
	}
}
