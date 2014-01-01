package br.com.vite.collection.grassland.objects;

import br.com.vite.tile.ImageTileLayer;

public class GraveNW extends ImageTileLayer {

	public GraveNW(long uniqueId) {
		super(uniqueId, "grassland/grassland.png");

		this.setLayerBounds(13*64, 13*32, 64, 64);
	}
	
}
