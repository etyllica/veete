package br.com.vite.editor;

import br.com.vite.map.HexagonalMap;
import br.com.vite.map.MapType;

public class HexagonalMapEditor extends MapEditor {

	public HexagonalMapEditor(int columns, int lines, int tileWidth, int tileHeight) {
		super(columns, lines, tileWidth, tileHeight);

		type = MapType.HEXAGONAL;

		map = new HexagonalMap(columns, lines, tileWidth, tileHeight);

		map.createTiles();
	}

	/*protected void updateTarget(int mouseX, int mouseY) {

	int column = Math.round((mouseX-offsetX)/tileWidth);

	int line = Math.round((mouseY-offsetY)/tileHeight);
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

	//for(int j=line-offset;j<line+offset;j++) {
	for(int j=0; j < lines; j++) {

		//for(int i=column-offset;i<column+offset;i++) {
		//for(int i = 0; i < columns; i++) {
		int i=0;

			if(colider.colideTile(tiles[j][i], mouseX, mouseY, offsetX, offsetY)) {

				target.setLocation(i, j);
				break;
			}
		//}
	}

}*/

	protected void updateTarget(int mouseX, int mouseY) {

		int columns = map.getColumns();

		int lines = map.getLines();

		int column = (int)((mouseX-map.getOffsetX())/(tileWidth*4)/3);

		int line = (int)((mouseY-map.getOffsetY())/tileHeight);

		boolean overLine = false;
		boolean overColumn = false;

		//onMouse = true;

		for(int j = 0;j < lines-1; j++) {

			for(int i = 0;i < columns-1; i++) {

				if(map.getColider().colideTile(map.getTiles()[j][i], mouseX, mouseY, map.getOffsetX(), map.getOffsetY())) {
					target.setLocation(i, j);
					break;
				}
			}
		}	
	}

}
