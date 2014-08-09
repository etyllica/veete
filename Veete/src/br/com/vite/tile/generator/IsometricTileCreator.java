package br.com.vite.tile.generator;

import br.com.vite.tile.IsometricTile;
import br.com.vite.tile.Tile;

public class IsometricTileCreator extends TileCreator {

	public IsometricTileCreator(int tileSize) {
		super(tileSize);
	}

	@Override
	public Tile createTile(int j, int i) {

		int oddOffsetX = (tileSize/2)*(j%2);

		return new IsometricTile(oddOffsetX+i*tileSize, (tileSize/4)*j, tileSize);

	}

}
