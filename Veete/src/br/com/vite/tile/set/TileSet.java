package br.com.vite.tile.set;

import br.com.etyllica.layer.ImageLayer;
import br.com.vite.map.MapType;

public class TileSet {

	private int tileWidth = 64;
	private int tileHeight = 64;
	
	private MapType type = MapType.ORTHOGONAL;
	
	private ImageLayer layer;
			
	public TileSet(int tileWidth, int tileHeight, MapType type) {
		super();
		
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.type = type;
	}
	
	public TileSet(int tileWidth, int tileHeight, MapType type, String path) {
		this(tileWidth, tileHeight, type);
				
		layer = new ImageLayer(path);
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}
	
	public ImageLayer getLayer() {
		return layer;
	}

	public MapType getType() {
		return type;
	}
	
}
