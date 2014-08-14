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
	
	protected void updateTarget(int mouseX, int mouseY) {

		int column = (int)(mouseX-offsetX)/tileWidth;

		int line = (int)(mouseY-offsetY)/(tileHeight/2);

		if(line<=0) {
			line = 1;
		}else if (line>=lines) {
			line = lines-1;
		}

		if(column<=0) {
			column = 1;
		}else if (column>=columns) {
			column = columns-1;
		}

		for(int j=line-1;j<line+1;j++) {

			for(int i=column-1;i<column+1;i++) {

				if(colider.colideTile(tiles[j][i],mouseX, mouseY, offsetX, offsetY)) {

					target.setLocation(i, j);
				}
			}
		}		
	}
	
}
