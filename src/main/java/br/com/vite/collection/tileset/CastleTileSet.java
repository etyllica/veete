package br.com.vite.collection.tileset;

import br.com.vite.map.MapType;
import br.com.vite.tile.set.ImageTileSet;

public class CastleTileSet extends ImageTileSet {

	public CastleTileSet(String id) {
		super(12, 9, 16, 16, MapType.ORTHOGONAL, "castle/tileset.png");
		this.id = id;
		createTiles();
	}

}
