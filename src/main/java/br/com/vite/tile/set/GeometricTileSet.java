package br.com.vite.tile.set;

import br.com.vite.map.MapType;


public class GeometricTileSet {
	
	protected int tileWidth = 64;
	protected int tileHeight = 64;	
	
	private MapType type = MapType.ORTHOGONAL;
	
	public GeometricTileSet(int tileWidth, int tileHeight) {
		super();
		
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}
	
	public GeometricTileSet(int tileWidth, int tileHeight, MapType type) {
		this(tileWidth, tileHeight);
		this.type = type;
	}
	
	public MapType getType() {
		return type;
	}
	
	
	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}
	
}