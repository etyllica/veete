package br.com.vite.tile.filler;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.tile.Tile;

public class OrthogonalTileFiller extends TileFiller {
	
	public OrthogonalTileFiller(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
	}

	@Override
	public void drawTileFiller(Tile tile, Graphic g, int offsetX, int offsetY) {

		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		g.setColor(color);
		g.fillRect(tx, ty, tile.getW(), tile.getH());
		
		if(floorTile != null) {
			floorTile.draw(g, tx, ty);
		}
	}
	
	@Override
	public void drawObjectFiller(Tile tile, Graphic g, int offsetX, int offsetY) {

		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
				
		if(objectTile != null) {
			objectTile.draw(g, tx, ty, tileWidth, tileHeight);
		}
	}
	
}
