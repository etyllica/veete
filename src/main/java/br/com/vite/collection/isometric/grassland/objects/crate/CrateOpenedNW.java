package br.com.vite.collection.isometric.grassland.objects.crate;

import br.com.vite.tile.layer.ImageTileFloor;

public class CrateOpenedNW extends ImageTileFloor {

	public CrateOpenedNW(String tileSetId) {
		super("grassland/grassland.png", tileSetId);

		this.setLayerBounds(1*64, 8*32, 64, 64);
	}
	
}
