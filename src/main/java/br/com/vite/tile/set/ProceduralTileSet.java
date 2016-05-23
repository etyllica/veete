package br.com.vite.tile.set;

import br.com.etyllica.layer.BufferedLayer;

public class ProceduralTileSet extends TileSet {
		
	protected BufferedLayer layer;
			
	public ProceduralTileSet(int columns, int lines, int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
		
		layer = new BufferedLayer(0, 0, columns*tileWidth, lines*tileHeight);
	}
		
	public BufferedLayer getLayer() {
		return layer;
	}
	
}
