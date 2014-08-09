package br.com.vite.collection.isometric.grassland.floor;

import br.com.vite.tile.ImageTileLayer;

public class Grass extends ImageTileLayer{

	public Grass(long uniqueId, int offsetTileX) {
		super(uniqueId, "grassland/grassland.png");

		this.setLayerBounds(offsetTileX*64, 0, 64, 32);
	}

}
