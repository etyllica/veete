package br.com.vite.tile.generator;

import br.com.vite.tile.Tile;
import br.com.vite.tile.TileHelper;

public abstract class TileCreator extends TileHelper {
	
	public TileCreator(int tileSizeX, int tileSizeY) {
		super(tileSizeX, tileSizeY);
		// TODO Auto-generated constructor stub
	}

	public abstract Tile createTile(int j, int i);

}
