package br.com.vite.collection.tileset.land;

import br.com.vite.map.MapType;
import br.com.vite.tile.collision.CollisionType;
import br.com.vite.tile.set.ImageTileSet;

public class LandTileSet extends ImageTileSet {
	
	public LandTileSet(String id) {
		super(8, 10, 16, 16, MapType.ORTHOGONAL, "platform/land.png");
		this.id = id;
		createTiles();
		
		tiles.get(getIndex(0, 0)).setCollision(CollisionType.BLOCK);
		tiles.get(getIndex(1, 0)).setCollision(CollisionType.UPPER);
		tiles.get(getIndex(2, 0)).setCollision(CollisionType.BLOCK);
		
		tiles.get(getIndex(0, 1)).setCollision(CollisionType.BLOCK);
		tiles.get(getIndex(1, 1)).setCollision(CollisionType.FREE);
		tiles.get(getIndex(2, 1)).setCollision(CollisionType.BLOCK);
		
		tiles.get(getIndex(0, 2)).setCollision(CollisionType.BLOCK);
		tiles.get(getIndex(1, 2)).setCollision(CollisionType.BLOCK);
		tiles.get(getIndex(2, 2)).setCollision(CollisionType.BLOCK);
		
		//
		tiles.get(getIndex(3, 0)).setCollision(CollisionType.UPPER_LEFT);
		tiles.get(getIndex(4, 0)).setCollision(CollisionType.UPPER_RIGHT);
		
		tiles.get(getIndex(3, 1)).setCollision(CollisionType.FREE);
		tiles.get(getIndex(4, 1)).setCollision(CollisionType.FREE);
		
		tiles.get(getIndex(3, 2)).setCollision(CollisionType.LOWER_LEFT);
		tiles.get(getIndex(4, 2)).setCollision(CollisionType.LOWER_RIGHT);
		
		tiles.get(getIndex(5, 0)).setCollision(CollisionType.UPPER_LEFT);
		tiles.get(getIndex(6, 0)).setCollision(CollisionType.UPPER_RIGHT);
		
		tiles.get(getIndex(5, 1)).setCollision(CollisionType.BLOCK);
		tiles.get(getIndex(6, 1)).setCollision(CollisionType.BLOCK);

		tiles.get(getIndex(7, 0)).setCollision(CollisionType.BLOCK);
		tiles.get(getIndex(7, 1)).setCollision(CollisionType.BLOCK);
		tiles.get(getIndex(7, 2)).setCollision(CollisionType.BLOCK);
	}

}
