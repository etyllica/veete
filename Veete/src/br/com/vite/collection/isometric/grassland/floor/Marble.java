package br.com.vite.collection.isometric.grassland.floor;

import br.com.vite.tile.ImageTileLayer;

public class Marble extends ImageTileLayer{

	public Marble(long uniqueId, int offsetTileX) {
		super(uniqueId, "grassland/grassland.png");
		
		this.setLayerBounds(offsetTileX*64, 19*32, 64, 32);
	}

}
