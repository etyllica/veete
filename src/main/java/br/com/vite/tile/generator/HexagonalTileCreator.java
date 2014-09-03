package br.com.vite.tile.generator;

import br.com.vite.tile.Tile;

public class HexagonalTileCreator extends TileCreator {

	public HexagonalTileCreator(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
	}

	@Override
	public Tile createTile(int j, int i) {

		int oddOffsetX = (tileWidth/2+tileWidth/4)*(j%2);

		return new Tile(oddOffsetX+i*(tileWidth+tileWidth/2), (tileHeight/2)*j, tileWidth, tileHeight);

	}

}
