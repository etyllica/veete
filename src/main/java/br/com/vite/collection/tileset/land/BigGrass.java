package br.com.vite.collection.tileset.land;

import br.com.vite.tile.layer.ImageTileObject;

public class BigGrass extends ImageTileObject {

	public BigGrass() {
		super("platform/land_grass.png");
		
		label = "big grass";
		
		int tileSize = 16;
		
		setLayerBounds(6*tileSize, 0, 10*tileSize, 6*tileSize);
		
		offsetX = 0;
		offsetY = 0;
	}
}
