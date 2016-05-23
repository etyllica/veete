package br.com.vite.collection.tileset.land;

import br.com.vite.map.MapType;
import br.com.vite.tile.collision.CollisionType;
import br.com.vite.tile.set.ImageTileSet;

public class LandTileSet extends ImageTileSet {
	
	public LandTileSet(String id) {
		super(10, 8, 16, 16, MapType.ORTHOGONAL, "platform/land.png");
		this.id = id;
		createTiles();
		
		collision[0][0] = CollisionType.BLOCK;
		collision[0][1] = CollisionType.UPPER;
		collision[0][2] = CollisionType.BLOCK;
		
		collision[1][0] = CollisionType.BLOCK;
		collision[1][1] = CollisionType.FREE;
		collision[1][2] = CollisionType.BLOCK;
		
		collision[2][0] = CollisionType.BLOCK;
		collision[2][1] = CollisionType.BLOCK;
		collision[2][2] = CollisionType.BLOCK;
		
		//
		collision[0][3] = CollisionType.UPPER_LEFT;
		collision[0][4] = CollisionType.UPPER_RIGHT;
		
		collision[1][3] = CollisionType.FREE;
		collision[1][4] = CollisionType.FREE;
		
		collision[2][3] = CollisionType.LOWER_LEFT;
		collision[2][4] = CollisionType.LOWER_RIGHT;
		
		collision[0][5] = CollisionType.UPPER_LEFT;
		collision[0][6] = CollisionType.UPPER_RIGHT;
		
		collision[1][5] = CollisionType.BLOCK;
		collision[1][6] = CollisionType.BLOCK;
		
		collision[7][0] = CollisionType.BLOCK;
		collision[7][1] = CollisionType.BLOCK;
		collision[7][2] = CollisionType.BLOCK;
		
	}

}
