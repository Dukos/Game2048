package main.java.model.boards;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Dimension {
	public final int width;
	public final int height;

	@Autowired
	public Dimension(@Value("5") int width, @Value("5") int height) {
		this.width = width;
		this.height = height;
	}

	public Dimension(Dimension other) {
		this.width = other.width;
		this.height = other.height;
	}

	@Override
	public int hashCode() {
		return Objects.hash(width, height);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Dimension other = (Dimension) obj;
		if (other.width != this.width)
			return false;
		if (other.height != this.height)
			return false;
		return true;
	}
}
