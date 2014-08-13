package br.com.vite.tile.drawer;

import java.awt.Color;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.tile.Tile;

public class OrthogonalTileDrawer extends TileDrawer {
	
	public OrthogonalTileDrawer(int tileSizeX, int tileSizeY) {
		super(tileSizeX, tileSizeY);		
	}
	
	@Override
	protected void drawFloor(Tile tile, Graphic g) {
		
		if(tile.getLayer() == null)
			return;
	
		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		tile.getLayer().draw(g, tx, ty);
		
	}

	@Override
	protected void drawGrid(Tile tile, Graphic g) {
		
		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		g.setColor(Color.BLACK);
		g.drawRect(tx, ty, tile.getW(), tile.getH());
	}

	@Override
	protected void drawObject(Tile tile, Graphic g) {
		
		if(tile.getObjectLayer() == null)
			return;
	
		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		tile.getObjectLayer().draw(g, tx, ty);		
		
	}

	
	
}
