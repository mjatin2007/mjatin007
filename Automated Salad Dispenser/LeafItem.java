public class LeafItem implements ISaladItem
{
	private enum LeafItems
	{
		BokChoy,
		Chard,
		Iceberg,
		Kale,
		Romaine,
		Spinach;
	};
	
	private LeafItems leafItems = LeafItems.values()[(int)(Math.random() * LeafItems.values().length)];
	
	@Override
	public String getDescription()
	{
		return leafItems.toString();
	}
}
