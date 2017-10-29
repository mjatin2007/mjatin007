package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.IStartObservable;

public class Start extends AbstractObserver implements IObservable{

	private static Start start = new Start();
	private ArrayList<IStartObservable> startobservables = new ArrayList<IStartObservable>();
	
	private Start() {
		// TODO Auto-generated method stub
		startObserver(this);
	}
	
	public static void startObserving (final IStartObservable iso){
		start .addObserver(iso);
	}
	
	private synchronized void addObserver(IStartObservable iso) {
		// TODO Auto-generated method stub
		if (iso!=null){
				if(startobservables.contains (iso)){
					startobservables.add(iso);
				}
		}
	}

	public static void start(){
		start.observed();
	}


	public static void stopObserving (final IStartObservable iso){
		start.removeObserver(iso);
	}

	private void removeObserver(IStartObservable iso) {
		// TODO Auto-generated method stub
		startobservables.remove(iso);	
		}
	
	public synchronized void update()
	{
		if (startobservables != null){
			Iterator<IStartObservable> iiso = startobservables.iterator();
			while(iiso.hasNext()){
				IStartObservable iso = iiso.next();
				if(iiso!=null)
				{
					iso.start();
				}
			}
		}
	}

}
