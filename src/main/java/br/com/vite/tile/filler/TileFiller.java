package br.com.vite.tile.filler;

import java.awt.Color;

import br.com.etyllica.core.graphics.Graphics;
import br.com.vite.tile.Tile;
import br.com.vite.tile.TileHelper;
import br.com.vite.tile.layer.ImageTileFloor;
import br.com.vite.tile.layer.ImageTileObject;

public abstract class TileFiller extends TileHelper {

	protected Color color = Color.GREEN;
	
	protected ImageTileFloor floorTile = null;
	
	protected ImageTileObject objectTile = null;
	
	public TileFiller(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
	}
		
	public abstract void drawTileFiller(Tile tile, Graphics g, int offsetX, int offsetY);
	
	public abstract void drawObjectFiller(Tile tile, Graphics g, int offsetX, int offsetY);
		
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ImageTileFloor getFloorTile() {
		return floorTile;
	}

	public void setFloorTile(ImageTileFloor floorTile) {
		this.floorTile = floorTile;
	}

	public void setObjectTile(ImageTileObject objectTile) {
		this.objectTile = objectTile;
	}	
			
}
