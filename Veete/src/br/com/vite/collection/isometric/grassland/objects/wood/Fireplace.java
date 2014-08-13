package br.com.vite.collection.isometric.grassland.objects.wood;

import br.com.vite.tile.layer.ImageTileFloor;

public class Fireplace extends ImageTileFloor {

	public Fireplace(long uniqueId) {
		super(uniqueId, "grassland/grassland.png");

		this.setLayerBounds(6*64, 8*32, 64, 64);
	}
	
}
