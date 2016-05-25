package br.com.vite.tile.set;

import br.com.etyllica.layer.BufferedLayer;
import br.com.vite.map.MapType;

public class ProceduralTileSet extends TileSet {
		
	protected BufferedLayer layer;
			
	public ProceduralTileSet(int rows, int columns, int tileWidth, int tileHeight, MapType type) {
		super(rows, columns, tileWidth, tileHeight, type);
		
		layer = new BufferedLayer(0, 0, columns*tileWidth, rows * tileHeight);
	}
		
	public BufferedLayer getLayer() {
		return layer;
	}
	
}
