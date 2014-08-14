package br.com.vite.tile.colider;

import br.com.vite.tile.Tile;
import br.com.vite.tile.TileHelper;

public abstract class TileColider extends TileHelper {
	
	public TileColider(int tileSizeX, int tileSizeY) {
		super(tileSizeX, tileSizeY);
	}

	public abstract boolean colideTile(Tile tile, int mx, int my, int offsetX, int offsetY);
	
}
