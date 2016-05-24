package br.com.vite.collection.isometric.grassland.objects.wood;

import br.com.vite.tile.layer.ImageTileFloor;

public class Fireplace extends ImageTileFloor {

	public Fireplace(String tileSetId) {
		super("grassland/grassland.png", tileSetId);

		this.setLayerBounds(6*64, 8*32, 64, 64);
	}
	
}
