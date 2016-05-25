package br.com.vite.collection.isometric.grassland.objects.wood;

import br.com.vite.tile.layer.ImageTileFloor;

public class FirewoodNE extends ImageTileFloor {

	public FirewoodNE(String tileSetId) {
		super("grassland/grassland.png", tileSetId);

		this.setLayerBounds(4*64, 8*32, 64, 64);
	}
	
}
