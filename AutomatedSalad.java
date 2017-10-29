import java.util.ArrayList;

public class AutomatedSalad implements ISaladObserver
{
	public static void main(String[] args) 
	{
		new AutomatedSalad().automate();
	}
	
	public void automate()
	{
		SaladObservable.addSaladObserver(this, 10);
	}

	@Override
	public void salad(final ArrayList<Order<SaladItem,ISaladItem>> salad)
	{
		//add this in your imports java.util.ListIterator if you want to use ListIterator

		System.out.print("Assembling Salad-->");//keep this line
		
		//TODO -YOUR CODE GOES HERE - Additional Factory is acceptable if you want to go that route
		System.out.print("[SALAD MACHINE IS DOWN -- NO SALAD ASSEMBLED]");//remove this line when done

		System.out.println("-->Salad Assembled");//keep this line
	}
}