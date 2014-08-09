package br.com.vite.collection.isometric.grassland.floor;

import br.com.vite.tile.ImageTileLayer;

public class GrassWithBricks extends ImageTileLayer{

	public GrassWithBricks(long uniqueId, int offsetTileX) {
		super(uniqueId, "grassland/grassland.png");
		
		this.setLayerBounds(offsetTileX*64, 1*32, 64, 32);
	}

}
