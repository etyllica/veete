package br.com.vite.collection.grassland.objects.wood;

import br.com.vite.tile.ImageTileLayer;

public class FirewoodNW extends ImageTileLayer {

	public FirewoodNW(long uniqueId) {
		super(uniqueId, "grassland/grassland.png");

		this.setLayerBounds(5*64, 8*32, 64, 64);
	}
	
}
