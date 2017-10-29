import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class SaladFactory
{
	private final static ArrayList<SaladItem> saladItems = new ArrayList<SaladItem>(SaladItem.values().length * 2);
	
	static
	{
		saladItems.add(SaladItem.Leaf    );
		saladItems.add(SaladItem.Leaf    );
		saladItems.add(SaladItem.Cheese  );
		saladItems.add(SaladItem.Cheese  );
		saladItems.add(SaladItem.Dressing);
		saladItems.add(SaladItem.Dressing);
		saladItems.add(SaladItem.Topping );
		saladItems.add(SaladItem.Topping );
	}
	
	public static synchronized ArrayList<Order<SaladItem,ISaladItem>> assembleSalad()
	{
		Collections.shuffle(saladItems);

		ArrayList<Order<SaladItem,ISaladItem>> orders = new ArrayList<Order<SaladItem,ISaladItem>>();
		
		int nItems  = (int)(Math.ceil(Math.random() * saladItems.size()));
        int nthItem = 0;
        
		Iterator<SaladItem> iSaladItems = saladItems.iterator();
		while ((++nthItem <= nItems) && (iSaladItems.hasNext()))
		{
			SaladItem saladItem = iSaladItems.next();
			switch (saladItem)
			{
				case Leaf:
					orders.add(new Order<SaladItem, ISaladItem>(saladItem, new LeafItem()));
				break;
				
				case Cheese:
					orders.add(new Order<SaladItem, ISaladItem>(saladItem, new CheeseItem()));
				break;
				
				case Dressing:
					orders.add(new Order<SaladItem, ISaladItem>(saladItem, new DressingItem()));
				break;
				
				case Topping:
					orders.add(new Order<SaladItem, ISaladItem>(saladItem, new ToppingItem()));
				break;
				
				default:
				break;
			}
		}
		
		return orders;
	}
}
      