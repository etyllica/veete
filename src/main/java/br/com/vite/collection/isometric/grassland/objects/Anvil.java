package br.com.vite.collection.isometric.grassland.objects;

import br.com.vite.tile.layer.ImageTileFloor;

public class Anvil extends ImageTileFloor {

	public Anvil(String tileSetId) {
		super("grassland/grassland.png", tileSetId);

		this.setLayerBounds(7*64, 8*32, 64, 64);
	}
	
}
