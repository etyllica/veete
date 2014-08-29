package br.com.vite.collection.isometric.grassland.objects.wood;

import br.com.vite.tile.layer.ImageTileFloor;

public class FirewoodNW extends ImageTileFloor {

	public FirewoodNW(long uniqueId) {
		super(uniqueId, "grassland/grassland.png");

		this.setLayerBounds(5*64, 8*32, 64, 64);
	}
	
}
