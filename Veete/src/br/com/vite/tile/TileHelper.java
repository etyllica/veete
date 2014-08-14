package br.com.vite.tile;

public class TileHelper {
	
	protected int tileSizeX = 0;
	
	protected int tileSizeY = 0;
	
	public TileHelper(int tileSizeX, int tileSizeY) {
		super();
		this.tileSizeX = tileSizeX;
		this.tileSizeY = tileSizeY;
	}

	public TileHelper(int tileSizeX, int tileSizeY, int offsetX, int offsetY) {
		super();
		this.tileSizeX = tileSizeX;
		this.tileSizeY = tileSizeY;
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
