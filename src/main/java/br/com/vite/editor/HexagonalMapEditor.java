package br.com.vite.editor;

import br.com.vite.map.HexagonalMap;

public class HexagonalMapEditor extends MapEditor {

	public HexagonalMapEditor(int columns, int rows, int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);

		map = new HexagonalMap(columns, rows, tileWidth, tileHeight);

		map.createTiles();
	}
	
}
