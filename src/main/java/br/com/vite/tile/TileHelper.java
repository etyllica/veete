package br.com.vite.tile;

public class TileHelper {
	
	protected int tileWidth = 0;
	
	protected int tileHeight = 0;
	
	public TileHelper(int tileWidth, int tileHeight) {
		super();
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}

	public TileHelper(int tileWidth, int tileHeight, int offsetX, int offsetY) {
		super();
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}

	public int getTileSizeX() {
		return tileWidth;
	}

	public void setTileSizeX(int tileSizeX) {
		this.tileWidth = tileSizeX;
	}

	public int getTileSizeY() {
		return tileHeight;
	}

	public void setTileSizeY(int tileSizeY) {
		this.tileHeight = tileSizeY;
	}	
		
}
