package br.com.vite.editor;

import br.com.vite.map.MapType;
import br.com.vite.map.OrthogonalMap;

public class OrthogonalMapEditor extends MapEditor {
	
	public OrthogonalMapEditor(int columns, int lines, int tileWidth, int tileHeight) {
		super(columns, lines, tileWidth, tileHeight);
		
		type = MapType.ORTHOGONAL;
		
		map = new OrthogonalMap(columns, lines, tileWidth, tileHeight);

		tiles = map.createTiles();
	}

}
