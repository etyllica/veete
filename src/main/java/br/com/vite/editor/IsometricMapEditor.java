package br.com.vite.editor;

import br.com.vite.map.IsometricMap;
import br.com.vite.map.MapType;

public class IsometricMapEditor extends MapEditor {
	
	public IsometricMapEditor(int columns, int lines, int tileWidth, int tileHeight) {
		super(columns, lines, tileWidth, tileHeight);
		
		type = MapType.ISOMETRIC;
		
		map = new IsometricMap(columns, lines, tileWidth, tileHeight);

		tiles = map.createTiles();
	}

}
