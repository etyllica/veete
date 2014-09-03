package br.com.vite.map.selection;

import br.com.vite.tile.layer.ImageTileFloor;
import br.com.vite.tile.layer.ImageTileObject;

public interface SelectionMapListener {

	public void setFloorTile(ImageTileFloor floor);
	
	public void setObjectTile(ImageTileObject object);
	
}
