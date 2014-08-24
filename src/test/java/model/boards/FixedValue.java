package test.java.model.boards;

public class FixedValue
{
	public final int value;
	public final int column;
	public final int row;
	
	public FixedValue(int row, int column, int value)
	{
		this.row = row;
		this.column = column;
		this.value = value;
	}
}
