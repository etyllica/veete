package br.com.vite.grassland;

import br.com.vite.tile.ImageTileLayer;

public class Grass2 extends ImageTileLayer{

	public Grass2(long uniqueId) {
		super(uniqueId, "grassland/grassland.png");
		
		this.setLayerBounds(64, 0, 64, 32);
	}

}
