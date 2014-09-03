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

		int offset = 1;
		
		boolean overLine = false;
		boolean overColumn = false;
		
		if(line < offset)
			line = offset;
		else if (line >= lines)
			line = lines - offset;
		else
			overLine = true;

		if(column < offset)
			column = offset;
		else if (column >= columns)
			column = columns - offset;
		else
			overColumn = true;
		
		onMouse = overLine&&overColumn;
		
		for(int j = line - offset;j<line+offset; j++) {

			for(int i = column - offset;i<column+offset; i++) {

				if(colider.colideTile(tiles[j][i],mouseX, mouseY, offsetX, offsetY)) {

					target.setLocation(i, j);
				}
			}
		}		
	}
	
}
