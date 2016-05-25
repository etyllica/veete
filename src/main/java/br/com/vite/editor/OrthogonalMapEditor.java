package br.com.vite.editor;

import br.com.vite.map.OrthogonalMap;

public class OrthogonalMapEditor extends MapEditor {
	
	public OrthogonalMapEditor(int columns, int rows, int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
				
		map = new OrthogonalMap(columns, rows, tileWidth, tileHeight);

		map.createTiles();
	}
	
}
