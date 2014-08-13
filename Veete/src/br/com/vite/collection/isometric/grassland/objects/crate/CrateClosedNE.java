package br.com.vite.collection.isometric.grassland.objects.crate;

import br.com.vite.tile.layer.ImageTileFloor;

public class CrateClosedNE extends ImageTileFloor {

	public CrateClosedNE(long uniqueId) {
		super(uniqueId, "grassland/grassland.png");

		this.setLayerBounds(2*64, 8*32, 64, 64);
	}
	
}
