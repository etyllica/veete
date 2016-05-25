package br.com.vite.collection.isometric.grassland.objects;

import br.com.vite.tile.layer.ImageTileFloor;

public class GraveNE extends ImageTileFloor {

	public GraveNE(String tileSetId) {
		super("grassland/grassland.png", tileSetId);

		this.setLayerBounds(12*64, 13*32, 64, 64);
	}
	
}
