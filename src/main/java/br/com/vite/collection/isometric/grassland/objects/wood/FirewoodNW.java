package br.com.vite.collection.isometric.grassland.objects.wood;

import br.com.vite.tile.layer.ImageTileFloor;

public class FirewoodNW extends ImageTileFloor {

	public FirewoodNW(String tileSetId) {
		super("grassland/grassland.png", tileSetId);

		this.setLayerBounds(5*64, 8*32, 64, 64);
	}
	
}
