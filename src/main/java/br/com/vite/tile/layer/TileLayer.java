package br.com.vite.tile.layer;

import br.com.vite.tile.collision.CollisionType;


public abstract class TileLayer {
	
	protected CollisionType collision = CollisionType.FREE;
	
	public TileLayer() {
		super();
	}
	
	public CollisionType getCollision() {
		return collision;
	}

	public void setCollision(CollisionType collision) {
		this.collision = collision;
	}
		
}
