import java.util.concurrent.ThreadFactory;

/**
*
*  The thread factory which takes a thread and gives it a name
*
*/

public class AutomatedSaladObservableFactory implements ThreadFactory
{
	private String observableName = null;

	/**
	 *
	 *  default constructor
	 *
	 *  @param the name for the observable
	 *
	 */
	
	public AutomatedSaladObservableFactory(final String sObservableName)
	{
		observableName = sObservableName;
	}
	
	/**
	 *
	 *  create a new thread given a runnable
	 *
	 *  @param the runnable for the thread
	 *
	 *  @return the newly constructed thread
	 *
	 */
	
	public Thread newThread(Runnable runnable)
	{
		return new Thread(runnable, observableName);
	}

	/**
	 *
	 *  gets the name of the observable
	 * 
	 * @return the threads name
	 *
	 */
	
	public final String getObservableName()
	{
		return observableName;
	}
}