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
	
	protected void updateTarget(int mouseX, int mouseY) {

		int column = (int)(mouseX-offsetX)/tileWidth;

		int line = (int)(mouseY-offsetY)/(tileHeight/2);

		boolean overLine = false;
		boolean overColumn = false;
		
		if(line<=0)
			line = 1;
		else if (line>=lines)
			line = lines-1;
		else
			overLine = true;

		if(column<=0)
			column = 1;
		else if (column>=columns)
			column = columns-1;
		else
			overColumn = true;
		
		onMouse = overLine&&overColumn;
		
		for(int j=line-1;j<line+1;j++) {

			for(int i=column-1;i<column+1;i++) {

				if(colider.colideTile(tiles[j][i],mouseX, mouseY, offsetX, offsetY)) {

					target.setLocation(i, j);
				}
			}
		}		
	}
	
}
