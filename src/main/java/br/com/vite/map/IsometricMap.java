package br.com.vite.map;

import br.com.etyllica.core.linear.PointInt2D;
import br.com.vite.tile.colider.IsometricTileColider;
import br.com.vite.tile.creator.IsometricTileCreator;
import br.com.vite.tile.drawer.IsometricTileDrawer;
import br.com.vite.tile.filler.IsometricTileFiller;

public class IsometricMap extends Map {

	public IsometricMap(int lines, int columns, int tileWidth, int tileHeight) {
		super(lines, columns, tileWidth, tileHeight);

		type = MapType.ISOMETRIC;
		
		creator = new IsometricTileCreator(tileWidth, tileHeight);
		collider = new IsometricTileColider(tileWidth, tileHeight);
		drawer = new IsometricTileDrawer(tileWidth, tileHeight);
		filler = new IsometricTileFiller(tileWidth, tileHeight);
	}
	
	public void getIndex(int px, int py, PointInt2D target) {
		int column = (int)(px-x)/tileWidth;
		int line = (int)(py-y)/(tileHeight/2);
		int offset = 1;

		int j = line - offset, i = column - offset;
		
		for(; j<line+offset; j++) {
			for(; i<column+offset; i++) {
				if(collider.colideTile(tiles[j][i], px, py, x, y)) {
					target.setLocation(i, j);
					return;
				}
			}
		}
	}
	
	public boolean updateTarget(int mouseX, int mouseY, PointInt2D target) {
		
		int column = (int)(mouseX-x)/tileWidth;
		int line = (int)(mouseY-y)/(tileHeight/2);
		
		int offset = 1;
		
		boolean overLine = false;
		boolean overColumn = false;
		
		if(line < offset)
			line = offset;
		else if (line >= rows)
			line = rows - offset;
		else
			overLine = true;

		if(column < offset)
			column = offset;
		else if (column >= columns)
			column = columns - offset;
		else
			overColumn = true;
		
		onTarget = overLine && overColumn;
		
		int j = line - offset, i = column - offset;
		
		for(; j<line+offset; j++) {
			for(; i<column+offset; i++) {
				if(collider.colideTile(tiles[j][i],mouseX, mouseY, x, y)) {
					target.setLocation(i, j);
					return true;
				}
			}
		}
		
		return false;
	}
	
}
