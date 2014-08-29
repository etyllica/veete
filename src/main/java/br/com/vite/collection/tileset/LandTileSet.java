package br.com.vite.collection.tileset;

import br.com.vite.map.MapType;
import br.com.vite.tile.set.TileSet;

public class LandTileSet extends TileSet {

	public LandTileSet() {
		super(16, 16, MapType.ORTHOGONAL, "platform/land.png");
	}

}
