package br.com.vite.collection.isometric.grassland.floor;

import br.com.vite.tile.layer.ImageTileFloor;

public class Marble extends ImageTileFloor{

	public Marble(int offsetTileX) {
		super("grassland/grassland.png");
		
		this.setLayerBounds(offsetTileX*64, 19*32, 64, 32);
	}

}
