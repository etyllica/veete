package br.com.vite.collection.tileset.land;

import br.com.vite.tile.layer.ImageTileObject;

public class SmallGrass extends ImageTileObject {

	public SmallGrass(String tileSetId) {
		super("platform/land_grass.png", tileSetId);
		
		label = "small grass";
		
		int tileSize = 16;
		
		setLayerBounds(0, 2*tileSize, 6*tileSize, 4*tileSize);
		
		offsetX = tileSize;
		offsetY = 0;
	}
}
