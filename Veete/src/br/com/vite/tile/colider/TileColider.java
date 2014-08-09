package br.com.vite.tile.colider;

import br.com.vite.tile.Tile;

public abstract class TileColider {

	protected int tileSizeX = 0;
	
	protected int tileSizeY = 0;
	
	protected int offsetX = 0;
	
	protected int offsetY = 0;
	
	public TileColider(int tileSizeX, int tileSizeY) {
		super();
		this.tileSizeX = tileSizeX;
		this.tileSizeY = tileSizeY;
	}
	
	public abstract boolean colideTile(Tile tile, int mx, int my);

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	public int getTileSizeX() {
		return tileSizeX;
	}

	public void setTileSizeX(int tileSizeX) {
		this.tileSizeX = tileSizeX;
	}

	public int getTileSizeY() {
		return tileSizeY;
	}

	public void setTileSizeY(int tileSizeY) {
		this.tileSizeY = tileSizeY;
	}	
	
}
