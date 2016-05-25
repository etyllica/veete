package br.com.vite.tile.creator;

import br.com.vite.tile.Tile;

public class OrthogonalTileCreator extends TileCreator {

	public OrthogonalTileCreator(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
	}

	@Override
	public Tile createTile(int j, int i) {

		return new Tile(i*tileWidth, j*tileHeight, tileWidth, tileHeight);
	}

}
