package br.com.vite.map;

import br.com.vite.tile.colider.OrthogonalTileColider;
import br.com.vite.tile.drawer.OrthogonalTileDrawer;
import br.com.vite.tile.filler.OrthogonalTileFiller;
import br.com.vite.tile.generator.OrthogonalTileCreator;

public class OrthogonalMap extends Map {
	
	public OrthogonalMap(int columns, int lines, int tileWidth, int tileHeight) {
		super(columns, lines, tileWidth, tileHeight);

		creator = new OrthogonalTileCreator(tileWidth, tileHeight);
		colider = new OrthogonalTileColider(tileWidth, tileHeight);
		drawer = new OrthogonalTileDrawer(tileWidth, tileHeight);
		filler = new OrthogonalTileFiller(tileWidth, tileHeight);		
	}		
}
