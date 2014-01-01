package br.com.vite.collection.grassland.objects.crate;

import br.com.vite.tile.ImageTileLayer;

public class CrateClosedNE extends ImageTileLayer {

	public CrateClosedNE(long uniqueId) {
		super(uniqueId, "grassland/grassland.png");

		this.setLayerBounds(2*64, 8*32, 64, 64);
	}
	
}
