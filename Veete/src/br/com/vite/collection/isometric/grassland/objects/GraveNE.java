package br.com.vite.collection.isometric.grassland.objects;

import br.com.vite.tile.ImageTileLayer;

public class GraveNE extends ImageTileLayer {

	public GraveNE(long uniqueId) {
		super(uniqueId, "grassland/grassland.png");

		this.setLayerBounds(12*64, 13*32, 64, 64);
	}
	
}
