package br.com.vite.map;

import br.com.vite.tile.colider.HexagonalTileColider;
import br.com.vite.tile.drawer.HexagonalTileDrawer;
import br.com.vite.tile.filler.HexagonalTileFiller;
import br.com.vite.tile.generator.HexagonalTileCreator;

public class HexagonalMap extends Map {

	public HexagonalMap(int lines, int columns, int tileWidth, int tileHeight) {
		super(lines, columns, tileWidth, tileHeight);

		creator = new HexagonalTileCreator(tileWidth, tileHeight);
		colider = new HexagonalTileColider(tileWidth, tileHeight);
		drawer = new HexagonalTileDrawer(tileWidth, tileHeight);
		filler = new HexagonalTileFiller(tileWidth, tileHeight);
	}		
}
