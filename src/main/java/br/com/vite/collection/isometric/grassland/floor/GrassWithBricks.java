package br.com.vite.collection.isometric.grassland.floor;

import br.com.vite.tile.layer.ImageTileFloor;

public class GrassWithBricks extends ImageTileFloor{

	public GrassWithBricks(String tileSetId, int offsetTileX) {
		super("grassland/grassland.png", tileSetId);
		
		this.setLayerBounds(offsetTileX*64, 1*32, 64, 32);
	}

}
