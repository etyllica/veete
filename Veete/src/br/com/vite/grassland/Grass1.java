package br.com.vite.grassland;

import br.com.vite.tile.ImageTileLayer;

public class Grass1 extends ImageTileLayer{

	public Grass1(long uniqueId) {
		super(uniqueId, "grassland/grassland.png");
		
		this.setLayerBounds(0, 0, 64, 32);
	}

}
