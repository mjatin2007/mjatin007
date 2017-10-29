package main.se450.observable;

import main.se450.interfaces.IObservable;

public class AbstractObserver {
	private IObservable ios = null;
	
	public AbstractObserver(){
		
	}
	public synchronized void startObserver (final IObservable iios){
		ios = iios;
	}
	public synchronized void stopObserver (final IObservable iios){
		if(isObserving(iios)){
			ios = null;
		}
	}
	
	public synchronized boolean isObserving(IObservable iios) {
		// TODO Auto-generated method stub
		return (ios == iios);
	}
	
	protected void observed(){
		if(ios != null){
			ios.update();
		}
	}
}
