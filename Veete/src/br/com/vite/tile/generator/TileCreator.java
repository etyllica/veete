package br.com.vite.tile.generator;

import br.com.vite.tile.Tile;

public abstract class TileCreator {
	
	protected int tileSize = 32;
	
	public TileCreator(int tileSize) {
		super();
		this.tileSize = tileSize;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
	
	public abstract Tile createTile(int j, int i);

}
