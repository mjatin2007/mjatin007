public class CheeseItem implements ISaladItem
{
	private enum CheeseItems
	{
		American,
		Asiago,
		Cheddar,
		HotPepper,
		Mozzarella,
		Parmesan,
		Swiss;
	};
	
	private CheeseItems cheeseItems = CheeseItems.values()[(int)(Math.random() * CheeseItems.values().length)];
	
	@Override
	public String getDescription()
	{
		return cheeseItems.toString();
	}
}