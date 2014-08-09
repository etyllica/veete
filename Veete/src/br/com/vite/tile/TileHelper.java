package br.com.vite.tile;

public class TileHelper {

	protected int offsetX = 0;
	
	protected int offsetY = 0;
	
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
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

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
