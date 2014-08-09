package br.com.vite.tile.generator;

import br.com.vite.tile.IsometricTile;
import br.com.vite.tile.Tile;

public class IsometricTileCreator extends TileCreator {

	public IsometricTileCreator(int tileSizeX, int tileSizeY) {
		super(tileSizeX, tileSizeY);
	}

	@Override
	public Tile createTile(int j, int i) {

		int oddOffsetX = (tileSizeX/2)*(j%2);

		return new IsometricTile(oddOffsetX+i*tileSizeX, (tileSizeY/2)*j, tileSizeX, tileSizeY);

	}

}
