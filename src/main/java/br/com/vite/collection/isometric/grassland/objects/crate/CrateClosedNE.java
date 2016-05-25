package br.com.vite.collection.isometric.grassland.objects.crate;

import br.com.vite.tile.layer.ImageTileFloor;

public class CrateClosedNE extends ImageTileFloor {

	public CrateClosedNE(String tileSetId) {
		super("grassland/grassland.png", tileSetId);

		this.setLayerBounds(2*64, 8*32, 64, 64);
	}
	
}
