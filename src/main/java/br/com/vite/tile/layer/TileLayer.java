package br.com.vite.tile.layer;

import br.com.vite.tile.collision.CollisionType;


public abstract class TileLayer {
	
	protected String id;
	protected String tileSetId;
	protected CollisionType collision = CollisionType.FREE;
	
	public TileLayer() {
		super();
	}
	
	public TileLayer(String tileSetId) {
		super();
		this.tileSetId = tileSetId;
	}

	public CollisionType getCollision() {
		return collision;
	}

	public void setCollision(CollisionType collision) {
		this.collision = collision;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getTileSetId() {
		return tileSetId;
	}

	public void setTileSetId(String tileSetId) {
		this.tileSetId = tileSetId;
	}
	
}
