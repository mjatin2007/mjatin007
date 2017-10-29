public class DressingItem implements ISaladItem
{
	private enum DressingItems
	{
		Buttermilk,
		Catalina,
		Cesar,
		Italian,
		Ranch,
		ThousandIsland,
        Vinaigrette 
	};
	
	private DressingItems dressingItems = DressingItems.values()[(int)(Math.random() * DressingItems.values().length)];
	
	@Override
	public String getDescription()
	{
		return dressingItems.toString();
	}
}