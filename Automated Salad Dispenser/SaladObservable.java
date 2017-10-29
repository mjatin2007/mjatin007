import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SaladObservable implements Runnable
{
	private static SaladObservable saladObservable = new SaladObservable();
	
	private boolean inSalad = false;
	private ScheduledThreadPoolExecutor scheduler = null;

	private ISaladObserver iSaladObserver = null;
	
	private int saladsToMake = 0;
	
	private SaladObservable()
	{
	}

	private static SaladObservable getSaladObservable()
	{
		return saladObservable;
	}
	
	public static void addSaladObserver(final ISaladObserver iiSaladObserver, int nSaladsToMake)
	{
		SaladObservable saladObservable = getSaladObservable();
		if (saladObservable != null)
			saladObservable.add(iiSaladObserver, nSaladsToMake);
	}
	
	private synchronized void add(final ISaladObserver iiSaladObserver, int nSaladsToMake)
	{
		iSaladObserver = iiSaladObserver;
		
		saladsToMake = nSaladsToMake;

		if (!getIsInSalad())
			startSalad();
	}
	
	public static void removeSaladObserver(final ISaladObserver iiSaladObserver)
	{
		SaladObservable saladObservable = getSaladObservable();
		if (saladObservable != null)
			saladObservable.remove(iiSaladObserver);
	}

	private synchronized void remove(final ISaladObserver iiSaladObserver)
	{
		if (iSaladObserver == iiSaladObserver)
		{
			iSaladObserver = null;
	
			stopSalad();
		}
	}
	
	private synchronized void startSalad()
	{
		setIsInSalad(true);
		
		if (scheduler == null)
		{
			AutomatedSaladObservableFactory automatedSaladObservableFactory = new AutomatedSaladObservableFactory("AutomatedSalad Observable");
			
			scheduler = new ScheduledThreadPoolExecutor(1, automatedSaladObservableFactory);
			if (scheduler != null)
				scheduler.scheduleAtFixedRate(this, 0, 1, TimeUnit.SECONDS);//1 salad every second
		}
	}

	private synchronized void stopSalad()
	{
		setIsInSalad(false);
		
		if (scheduler != null)
		{
			scheduler.shutdown();
			
			scheduler = null;
		}
	}
	
	public static final boolean isInSalad()
	{
		SaladObservable saladObservable = getSaladObservable();
		return (saladObservable != null ? saladObservable.getIsInSalad() : false);
	}

	public final synchronized boolean getIsInSalad()
	{
		return inSalad;
	}
	
	private final synchronized void setIsInSalad(final boolean bIsInSalad)
	{
		inSalad = bIsInSalad;
	}
	
	public synchronized void run()
	{
		if (--saladsToMake < 0)
		{
			stopSalad();
		}
		else
		{
			if ((iSaladObserver != null) && (isInSalad()))
			{
				iSaladObserver.salad(SaladFactory.assembleSalad());
			}
		}
	}	
}
