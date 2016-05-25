package br.com.vite.tile.set;

import br.com.etyllica.layer.ImageLayer;
import br.com.vite.map.MapType;

public class ImageTileSet extends TileSet {
	
	private ImageLayer layer;
				
	public ImageTileSet(int rows, int columns, int tileWidth, int tileHeight, MapType type) {
		super(rows, columns, tileWidth, tileHeight, type);
	}
		
	public ImageTileSet(int rows, int columns, int tileWidth, int tileHeight, MapType type, String path) {
		this(rows, columns, tileWidth, tileHeight, type);
		
		this.path = path;
		layer = new ImageLayer(path);
	}
	
	public ImageLayer getLayer() {
		return layer;
	}

}
