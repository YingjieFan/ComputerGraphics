package hw2_2;

import java.awt.Color;

public class ElementGrid {
	private int pattern;
	private Color color;
	private boolean shouldUpdate;
	private boolean updated;
	private boolean isFreezed;
	public boolean isFreezed() {
		return isFreezed;
	}
	public void setFreezed(boolean isFreezed) {
		this.isFreezed = isFreezed;
	}
	public boolean isUpdated() {
		return updated;
	}
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}
	public ElementGrid() {
		super();
		// TODO Auto-generated constructor stub
		shouldUpdate=false;
		pattern=-1;
		color=Color.BLACK;
		updated=false;
		isFreezed = false;

	}
	public boolean shouldUpdate(){
		return shouldUpdate;
	}
	public void setShouldUpdate(Boolean shouldUpdate){
		this.shouldUpdate=shouldUpdate;
	}
	public int getPattern() {
		return pattern;
	}
	public void setPattern(int pattern) {
		this.pattern = pattern;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public void clearGrid(){
		this.pattern=-1;
		color=Color.BLACK;
		shouldUpdate=false;
		updated=false;
		isFreezed = false;

	}
	
}
