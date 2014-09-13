package br.com.vite.tile.drawer;

import java.awt.Color;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.graphics.SVGColor;
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
			
			g.setColor(SVGColor.GREEN);
			
		} else if(tile.getCollision() == CollisionType.UPPER 
				|| tile.getCollision() == CollisionType.UPPER_LEFT 
				|| tile.getCollision() == CollisionType.UPPER_RIGHT
				|| tile.getCollision() == CollisionType.LOWER_LEFT
				|| tile.getCollision() == CollisionType.LOWER_RIGHT) {			
			
			g.setColor(SVGColor.ORANGE_RED);
			
		} else {
			
			g.setColor(SVGColor.RED);
			
		}
		
		g.fillRect(tx, ty, tile.getW(), tile.getH());
	}
	
}
