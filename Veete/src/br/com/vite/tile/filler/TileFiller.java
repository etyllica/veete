package br.com.vite.tile.filler;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.tile.Tile;
import br.com.vite.tile.TileHelper;

public abstract class TileFiller extends TileHelper {

	public TileFiller(int tileSizeX, int tileSizeY) {
		super(tileSizeX, tileSizeY);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void drawFiller(Tile tile, Graphic g);
			
}
