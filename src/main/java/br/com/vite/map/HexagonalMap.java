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

		int column = Math.round((mouseX-offsetX)/tileWidth/1.5f);

		int line = Math.round((mouseY-offsetY)/tileHeight*1.5f);
		//line++;
				
		int offset = 2;
		
		System.out.println("line: "+line);
		System.out.println("column: "+column);
		
		boolean overLine = false;
		boolean overColumn = false;
		
		if(line<=offset)
			line = offset;
		else if (line >= lines)
			line = lines - offset;
		else
			overLine = true;

		if(column <= offset)
			column = offset;
		else if (column >= columns)
			column = columns - offset;
		else
			overColumn = true;
		
		onMouse = overLine&&overColumn;
		
		//target.setLocation(column, line);
				
		for(int j=line-offset;j<line+offset;j++) {

			for(int i=column-offset;i<column+offset;i++) {

				if(colider.colideTile(tiles[j][i], mouseX, mouseY, offsetX, offsetY)) {

					target.setLocation(i, j);
				}
			}
		}		
	}
	
}
