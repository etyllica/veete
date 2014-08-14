package br.com.vite.tile.filler;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.tile.Tile;
import br.com.vite.tile.TileHelper;

public abstract class TileFiller extends TileHelper {

	public TileFiller(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
	}
	
	public abstract void drawFiller(Tile tile, Graphic g, int offsetX, int offsetY);
			
}
