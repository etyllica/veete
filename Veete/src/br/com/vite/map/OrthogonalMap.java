package br.com.vite.map;

import br.com.vite.tile.colider.OrthogonalTileColider;
import br.com.vite.tile.drawer.OrthogonalTileDrawer;
import br.com.vite.tile.filler.OrthogonalTileFiller;
import br.com.vite.tile.generator.OrthogonalTileCreator;

public class OrthogonalMap extends Map {
	
	public OrthogonalMap(int lines, int columns, int tileWidth, int tileHeight) {
		super(lines, columns, tileWidth, tileHeight);

		creator = new OrthogonalTileCreator(tileWidth, tileHeight);
		colider = new OrthogonalTileColider(tileWidth, tileHeight);
		drawer = new OrthogonalTileDrawer(tileWidth, tileHeight);
		filler = new OrthogonalTileFiller(tileWidth, tileHeight);		
	}
	
	protected void updateTarget(int mouseX, int mouseY) {

		int column = (int)((mouseX-offsetX)/tileWidth);

		int line = (int)((mouseY-offsetY)/tileHeight);

		if(line < 0)
			line = 0;
		else if(line >= tiles.length)
			line = tiles.length-1; 

		if(column < 0)
			column = 0;
		else if(column >= tiles[0].length)
			column = tiles[0].length-1;

		int j = line;
		int i = column;

		target.setLocation(i, j);

	}
	
}