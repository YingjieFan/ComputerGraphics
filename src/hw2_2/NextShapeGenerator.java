package hw2_2;

import java.util.concurrent.ThreadLocalRandom;

public class NextShapeGenerator {
	private int shape = -1;
	public int randomShape(){
		return this.shape = ThreadLocalRandom.current().nextInt(0, 4 + 1);
	}
}
