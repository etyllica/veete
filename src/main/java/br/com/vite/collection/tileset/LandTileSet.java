package br.com.vite.collection.tileset;

import br.com.vite.map.MapType;
import br.com.vite.tile.collision.CollisionType;
import br.com.vite.tile.set.TileSet;

public class LandTileSet extends TileSet {
	
	public LandTileSet() {
		super(10, 8, 16, 16, MapType.ORTHOGONAL, "platform/land.png");
		
		collision[0][0] = CollisionType.UPPER_LEFT;
		collision[0][1] = CollisionType.UPPER;
		collision[0][2] = CollisionType.UPPER_RIGHT;
		
		collision[1][0] = CollisionType.BLOCK;
		collision[1][1] = CollisionType.BLOCK;
		collision[1][2] = CollisionType.BLOCK;
		
		collision[2][0] = CollisionType.LOWER_LEFT;
		collision[2][1] = CollisionType.LOWER;
		collision[2][2] = CollisionType.LOWER_RIGHT;
	}

}
