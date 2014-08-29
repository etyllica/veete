package br.com.vite.collection.orthogonal.gothic;

import br.com.vite.tile.layer.ImageTileObject;

public class Column extends ImageTileObject {

	public Column(long uniqueId) {
		super(uniqueId, "castle/column.png");
		
		offsetX = 32;
		offsetY = 111-32;
	}

}
