package br.com.vite.collection.isometric.grassland.objects.crate;

import br.com.vite.tile.ImageTileLayer;

public class CrateOpenedNW extends ImageTileLayer {

	public CrateOpenedNW(long uniqueId) {
		super(uniqueId, "grassland/grassland.png");

		this.setLayerBounds(1*64, 8*32, 64, 64);
	}
	
}
