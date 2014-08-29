package br.com.vite.tile.filler;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.tile.Tile;
import br.com.vite.tile.TileHelper;
import br.com.vite.tile.layer.ImageTileFloor;

public abstract class TileFiller extends TileHelper {

	protected ImageTileFloor floorTile = null;
	
	public TileFiller(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
	}
		
	public abstract void drawFiller(Tile tile, Graphic g, int offsetX, int offsetY);
	
	public ImageTileFloor getFloorTile() {
		return floorTile;
	}

	public void setFloorTile(ImageTileFloor floorTile) {
		this.floorTile = floorTile;
	}	
			
}