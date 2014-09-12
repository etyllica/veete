package br.com.vite.tile.set;

import br.com.etyllica.layer.ImageLayer;
import br.com.vite.map.MapType;
import br.com.vite.tile.collision.CollisionType;

public class TileSet extends GeometricTileSet {
		
	private int columns = 0;
	
	private int lines = 0;
	
	private ImageLayer layer;
	
	protected CollisionType[][] collision;
				
	public TileSet(int columns, int lines, int tileWidth, int tileHeight, MapType type) {
		super(tileWidth, tileHeight, type);
		
		this.columns = columns;
		this.lines = lines;
		
		initCollisions(columns, lines);
	}
		
	public TileSet(int columns, int lines, int tileWidth, int tileHeight, MapType type, String path) {
		this(columns, lines, tileWidth, tileHeight, type);
				
		layer = new ImageLayer(path);
	}
	
	private void initCollisions(int columns, int lines) {
		
		collision = new CollisionType[lines][columns];
		
		for (int j = 0; j < lines; j++) {
			for (int i = 0; i < columns; i++) {
				collision[j][i] = CollisionType.FREE;
			}
		}
	}
	
	public ImageLayer getLayer() {
		return layer;
	}

	public int getColumns() {
		return columns;
	}

	public int getLines() {
		return lines;
	}

	public CollisionType[][] getCollision() {
		return collision;
	}
	
}
