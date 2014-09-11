package br.com.vite.tile.set;

import br.com.etyllica.layer.ImageLayer;
import br.com.vite.map.MapType;

public class TileSet extends GeometricTileSet {
		
	private ImageLayer layer;
			
	public TileSet(int tileWidth, int tileHeight, MapType type) {
		super(tileWidth, tileHeight, type);
	}
	
	public TileSet(int tileWidth, int tileHeight, MapType type, String path) {
		this(tileWidth, tileHeight, type);
				
		layer = new ImageLayer(path);
	}
	
	public ImageLayer getLayer() {
		return layer;
	}
	
}
