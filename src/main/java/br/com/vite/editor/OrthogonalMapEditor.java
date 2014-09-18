package br.com.vite.editor;

import br.com.vite.map.MapType;
import br.com.vite.map.OrthogonalMap;

public class OrthogonalMapEditor extends MapEditor {
	
	public OrthogonalMapEditor(int columns, int lines, int tileWidth, int tileHeight) {
		super(columns, lines, tileWidth, tileHeight);
		
		type = MapType.ORTHOGONAL;
		
		map = new OrthogonalMap(columns, lines, tileWidth, tileHeight);

		map.createTiles();
	}
	
	protected void updateTarget(int mouseX, int mouseY) {

		int columns = map.getColumns();
		
		int lines = map.getLines();
		
		int column = (int)((mouseX-map.getOffsetX())/tileWidth);

		int line = (int)((mouseY-map.getOffsetY())/tileHeight);

		boolean overLine = false;
		boolean overColumn = false;
		
		if(line < 0)
			line = 0;
		else if(line >= lines)
			line = lines-1;
		else
			overLine = true;
		
		if(column < 0)
			column = 0;
		else if(column >= columns)
			column = columns-1;
		else
			overColumn = true;
		
		onMouse = overLine&&overColumn;

		int j = line;
		int i = column;

		target.setLocation(i, j);
	}

}
