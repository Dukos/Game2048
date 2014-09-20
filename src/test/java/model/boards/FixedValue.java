package test.java.model.boards;

import java.awt.Point;

public class FixedValue {
	public final int x;
	public final int y;
	public final int value;

	public FixedValue(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}

	public FixedValue(Point point, int value) {
		this.x = point.x;
		this.y = point.y;
		this.value = value;
	}
}
