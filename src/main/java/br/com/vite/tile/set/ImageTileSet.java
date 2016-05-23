package br.com.vite.tile.set;

import br.com.etyllica.core.linear.PointInt2D;
import br.com.etyllica.layer.ImageLayer;
import br.com.vite.map.MapType;
import br.com.vite.map.selection.SelectedTile;
import br.com.vite.tile.collision.CollisionType;

public class ImageTileSet extends TileSet {
	
	private ImageLayer layer;
				
	public ImageTileSet(int columns, int rows, int tileWidth, int tileHeight, MapType type) {
		super(tileWidth, tileHeight, columns, rows, type);
	}
		
	public ImageTileSet(int columns, int lines, int tileWidth, int tileHeight, MapType type, String path) {
		this(columns, lines, tileWidth, tileHeight, type);
		
		layer = new ImageLayer(path);
	}
	
	public ImageTileSet createTiles() {
		collision = new CollisionType[rows][columns];
		
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < columns; i++) {
				collision[j][i] = CollisionType.FREE;
				
				String tileId = generateId();
				tileIds.put(new PointInt2D(i, j), tileId);
				
				int x = i*tileWidth;
				int y = j*tileHeight;
				
				tiles.put(tileId, new SelectedTile(layer.getPath(), x, y, tileWidth, tileHeight));
			}
		}
		
		return this;
	}

	public ImageLayer getLayer() {
		return layer;
	}

}
