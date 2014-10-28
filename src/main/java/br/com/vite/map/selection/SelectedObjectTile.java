package br.com.vite.map.selection;

import br.com.vite.tile.collision.CollisionType;
import br.com.vite.tile.layer.ImageTileObject;

public class SelectedObjectTile extends SelectedTile {

	protected int offsetX = 0;
	
	protected int offsetY = 0;
	
	protected String label;		
		
	public SelectedObjectTile(String path, int x, int y, CollisionType collision) {
		super(path, x, y, collision);
	}
	
	public SelectedObjectTile(ImageTileObject obj) {
		super(obj.getPath(), obj.getX(), obj.getY(), obj.getCollision());
		
		this.label = obj.getLabel();
		this.offsetX = obj.getOffsetX();
		this.offsetY = obj.getOffsetY();
		this.width = obj.getW();
		this.height = obj.getH();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
	
}
