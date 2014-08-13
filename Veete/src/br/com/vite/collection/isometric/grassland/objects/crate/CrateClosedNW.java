package br.com.vite.collection.isometric.grassland.objects.crate;

import br.com.vite.tile.layer.ImageTileFloor;

public class CrateClosedNW extends ImageTileFloor {

	public CrateClosedNW(long uniqueId) {
		super(uniqueId, "grassland/grassland.png");

		this.setLayerBounds(0*64, 8*32, 64, 64);
	}
	
}
