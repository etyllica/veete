package br.com.vite.editor;

import br.com.vite.map.IsometricMap;
import br.com.vite.map.MapType;

public class IsometricMapEditor extends MapEditor {
	
	public IsometricMapEditor(int columns, int lines, int tileWidth, int tileHeight) {
		super(columns, lines, tileWidth, tileHeight);
		
		type = MapType.ISOMETRIC;
		
		map = new IsometricMap(columns, lines, tileWidth, tileHeight);

		map.createTiles();
	}

	protected void updateTarget(int mouseX, int mouseY) {

		int columns = map.getColumns();
		
		int lines = map.getLines();
		
		int column = (int)(mouseX-map.getOffsetX())/tileWidth;

		int line = (int)(mouseY-map.getOffsetY())/(tileHeight/2);
		
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
		
		for(int j = line - offset; j<line+offset; j++) {

			for(int i = column - offset; i<column+offset; i++) {

				if(map.getColider().colideTile(map.getTiles()[j][i],mouseX, mouseY, map.getOffsetX(), map.getOffsetY())) {
					target.setLocation(i, j);
					break;
				}
			}
		}
	}	
}
