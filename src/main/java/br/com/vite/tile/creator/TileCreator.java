package br.com.vite.tile.creator;

import br.com.vite.tile.Tile;
import br.com.vite.tile.TileHelper;

public abstract class TileCreator extends TileHelper {
	
	public TileCreator(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
	}

	public abstract Tile createTile(int j, int i);

}
