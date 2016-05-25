package br.com.vite.collection.isometric.grassland.objects;

import br.com.vite.tile.layer.ImageTileFloor;

public class GraveNW extends ImageTileFloor {

	public GraveNW(String tileSetId) {
		super("grassland/grassland.png", tileSetId);

		this.setLayerBounds(13*64, 13*32, 64, 64);
	}
	
}
