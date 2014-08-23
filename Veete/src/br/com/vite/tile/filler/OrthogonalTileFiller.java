package br.com.vite.tile.filler;

import java.awt.Color;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.tile.Tile;

public class OrthogonalTileFiller extends TileFiller {
	
	public OrthogonalTileFiller(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
	}

	@Override
	public void drawFiller(Tile tile, Graphic g, int offsetX, int offsetY) {

		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		g.setColor(Color.GREEN);
		g.fillRect(tx, ty, tile.getW(), tile.getH());
		
		if(floorTile!=null) {
			floorTile.draw(g, tx, ty);
		}
	}
	
}
