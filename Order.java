public class Order<T1,T2> 
{
	private SaladItem  saladItem  = null;
	private ISaladItem iSaladItem = null; 

	public Order(SaladItem oSaladItem, ISaladItem iiSaladItem) 
	{
		saladItem  = oSaladItem;
		iSaladItem = iiSaladItem;
	}
	
	public SaladItem getSaladItem()
	{
		return saladItem;
	}

	public ISaladItem getIItem()
	{
		return iSaladItem;
	}
}