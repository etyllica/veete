package br.com.vite.map;

import br.com.vite.tile.colider.IsometricTileColider;
import br.com.vite.tile.drawer.IsometricTileDrawer;
import br.com.vite.tile.filler.IsometricTileFiller;
import br.com.vite.tile.generator.IsometricTileCreator;

public class IsometricMap extends Map {

	public IsometricMap(int lines, int columns, int tileWidth, int tileHeight) {
		super(lines, columns, tileWidth, tileHeight);

		creator = new IsometricTileCreator(tileWidth, tileHeight);
		colider = new IsometricTileColider(tileWidth, tileHeight);
		drawer = new IsometricTileDrawer(tileWidth, tileHeight);
		filler = new IsometricTileFiller(tileWidth, tileHeight);
	}		
}
