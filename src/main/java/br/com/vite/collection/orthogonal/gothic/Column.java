package br.com.vite.collection.orthogonal.gothic;

import br.com.vite.tile.layer.ImageTileObject;

public class Column extends ImageTileObject {

	public Column(String tileSetId) {
		super("castle/column.png", tileSetId);
		
		offsetX = 32;
		offsetY = 111-32;
	}

}
