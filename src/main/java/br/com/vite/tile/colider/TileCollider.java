package br.com.vite.tile.colider;

import br.com.vite.tile.Tile;
import br.com.vite.tile.TileHelper;

public abstract class TileCollider extends TileHelper {
	
	public TileCollider(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
	}

	public abstract boolean colideTile(Tile tile, int mx, int my, int offsetX, int offsetY);
	
}
