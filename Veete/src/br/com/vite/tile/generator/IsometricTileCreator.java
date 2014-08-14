package br.com.vite.tile.generator;

import br.com.vite.tile.Tile;

public class IsometricTileCreator extends TileCreator {

	public IsometricTileCreator(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
	}

	@Override
	public Tile createTile(int j, int i) {

		int oddOffsetX = (tileWidth/2)*(j%2);

		return new Tile(oddOffsetX+i*tileWidth, (tileHeight/2)*j, tileWidth, tileHeight);

	}

}
