package br.com.vite.collection.grassland.objects.crate;

import br.com.vite.tile.ImageTileLayer;

public class CrateClosedNW extends ImageTileLayer {

	public CrateClosedNW(long uniqueId) {
		super(uniqueId, "grassland/grassland.png");

		this.setLayerBounds(0*64, 8*32, 64, 64);
	}
	
}
