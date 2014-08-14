package br.com.vite.tile.drawer;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.tile.Tile;
import br.com.vite.tile.TileHelper;

public abstract class TileDrawer extends TileHelper {
	
	protected boolean drawGrid = true;

	public TileDrawer(int tileSizeX, int tileSizeY) {
		super(tileSizeX, tileSizeY);
	}
	
	public void drawTile(Tile tile, Graphic g, int offsetX, int offsetY) {
		
		drawFloor(tile, g, offsetX, offsetY);

		if(drawGrid)
			drawGrid(tile, g, offsetX, offsetY);
		
		drawObject(tile, g, offsetX, offsetY);
	}
	
	protected abstract void drawGrid(Tile tile, Graphic g, int offsetX, int offsetY);
	
	private void drawFloor(Tile tile, Graphic g, int offsetX, int offsetY) {
		
		if(tile.getLayer() == null)
			return;
	
		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		tile.getLayer().draw(g, tx, ty);		
	}
	
	private void drawObject(Tile tile, Graphic g, int offsetX, int offsetY) {
		
		if(tile.getObjectLayer() == null)
			return;
			
		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;		
		
		tile.getObjectLayer().draw(g, tx, ty, tile.getW(), tile.getH());		
	}

}
