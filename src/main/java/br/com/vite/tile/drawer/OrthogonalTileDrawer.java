package br.com.vite.tile.drawer;

import java.awt.Color;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.tile.Tile;
import br.com.vite.tile.collision.CollisionType;

public class OrthogonalTileDrawer extends TileDrawer {
	
	public OrthogonalTileDrawer(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
	}
	
	@Override
	protected void drawGrid(Tile tile, Graphic g, int offsetX, int offsetY) {
		
		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		g.setColor(Color.BLACK);
		g.drawRect(tx, ty, tile.getW(), tile.getH());
	}
	
	@Override
	protected void drawCollision(Tile tile, Graphic g, int offsetX, int offsetY) {

		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		if(tile.getCollision() == CollisionType.FREE) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.RED);
		}
		
		g.fillRect(tx, ty, tile.getW(), tile.getH());
	}
	
}
