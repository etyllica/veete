package br.com.vite.collection.isometric.grassland.objects.wood;

import br.com.vite.tile.ImageTileLayer;

public class FirewoodNE extends ImageTileLayer {

	public FirewoodNE(long uniqueId) {
		super(uniqueId, "grassland/grassland.png");

		this.setLayerBounds(4*64, 8*32, 64, 64);
	}
	
}
