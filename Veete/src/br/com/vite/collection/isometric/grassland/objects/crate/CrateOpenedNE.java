package br.com.vite.collection.isometric.grassland.objects.crate;

import br.com.vite.tile.ImageTileLayer;

public class CrateOpenedNE extends ImageTileLayer {

	public CrateOpenedNE(long uniqueId) {
		super(uniqueId, "grassland/grassland.png");

		this.setLayerBounds(3*64, 8*32, 64, 64);
	}
	
}
