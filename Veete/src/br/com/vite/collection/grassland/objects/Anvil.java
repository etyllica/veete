package br.com.vite.collection.grassland.objects;

import br.com.vite.tile.ImageTileLayer;

public class Anvil extends ImageTileLayer {

	public Anvil(long uniqueId) {
		super(uniqueId, "grassland/grassland.png");

		this.setLayerBounds(7*64, 8*32, 64, 64);
	}
	
}
