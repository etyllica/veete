package br.com.vite.editor;

import br.com.vite.map.IsometricMap;

public class IsometricMapEditor extends MapEditor {
	
	public IsometricMapEditor(int columns, int lines, int tileWidth, int tileHeight) {
		super(columns, lines, tileWidth, tileHeight);
				
		map = new IsometricMap(columns, lines, tileWidth, tileHeight);

		map.createTiles();
	}
}
