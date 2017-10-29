public abstract class Automation implements IAutomation
{
	private IAutomation iOutput = null;
	
	@Override
	public void processInput()
	{
		if (iOutput != null)
		{
			iOutput.processInput();
		}
	}

	@Override
	public IAutomation getOutput() 
	{
		return iOutput;
	}
	
	@Override
	public void setOutput(IAutomation iAutomation) 
	{
		iOutput = iAutomation;
	}
}
