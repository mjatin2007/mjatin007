public class ToppingItem implements ISaladItem
{
	private enum ToppingItems
	{
		BaconBits,
		Croutons,
		Eggs,
		Tomatoes,
		SesameSeeds,
		Raisins,
		Onions,
		Cucumbers,
		Beets,
		Chicken,
		Ham,
		Tuna		
	};
	
	private ToppingItems toppingItems = ToppingItems.values()[(int)(Math.random() * ToppingItems.values().length)];
	
	@Override
	public String getDescription()
	{
		return toppingItems.toString();
	}
}
