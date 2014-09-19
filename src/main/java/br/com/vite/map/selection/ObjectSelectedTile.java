package br.com.vite.map.selection;

import br.com.vite.tile.collision.CollisionType;

public class ObjectSelectedTile extends SelectedTile {

	protected String label; 
	
	public ObjectSelectedTile(String path, int x, int y, CollisionType collision) {
		super(path, x, y, collision);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
