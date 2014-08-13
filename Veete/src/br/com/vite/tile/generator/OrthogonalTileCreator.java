package br.com.vite.tile.generator;

import br.com.vite.tile.Tile;

public class OrthogonalTileCreator extends TileCreator {

	public OrthogonalTileCreator(int tileSizeX, int tileSizeY) {
		super(tileSizeX, tileSizeY);
	}

	@Override
	public Tile createTile(int j, int i) {

		return new Tile(i*tileSizeX, j*tileSizeY, tileSizeX, tileSizeY);
	}

}
