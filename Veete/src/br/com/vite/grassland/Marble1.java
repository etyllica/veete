package br.com.vite.grassland;

import br.com.vite.tile.ImageTileLayer;

public class Marble1 extends ImageTileLayer{

	public Marble1(long uniqueId) {
		super(uniqueId, "grassland/grassland.png");
		
		this.setLayerBounds(0, 19*32, 64, 32);
	}

}
