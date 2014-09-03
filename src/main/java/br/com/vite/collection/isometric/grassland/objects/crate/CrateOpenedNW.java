package br.com.vite.collection.isometric.grassland.objects.crate;

import br.com.vite.tile.layer.ImageTileFloor;

public class CrateOpenedNW extends ImageTileFloor {

	public CrateOpenedNW() {
		super("grassland/grassland.png");

		this.setLayerBounds(1*64, 8*32, 64, 64);
	}
	
}
