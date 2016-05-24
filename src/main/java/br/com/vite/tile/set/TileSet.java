package br.com.vite.tile.set;

import java.util.HashMap;

import br.com.etyllica.core.linear.PointInt2D;
import br.com.vite.map.MapType;
import br.com.vite.map.selection.SelectedTile;
import br.com.vite.tile.collision.CollisionType;
import br.com.vite.tile.layer.ImageTileFloor;

public class TileSet {
	
	private int count = 0;
	protected String id;
	protected String path;
	
	protected int tileWidth = 64;
	protected int tileHeight = 64;
	
	protected int columns = 0;
	protected int rows = 0;
	
	private MapType type = MapType.ORTHOGONAL;
		
	protected java.util.Map<PointInt2D, String> tileIds = new HashMap<PointInt2D, String>();
	protected java.util.Map<String, SelectedTile> tiles = new HashMap<String, SelectedTile>();
		
	public TileSet(int rows, int columns, int tileWidth, int tileHeight, MapType type) {
		super();
		
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.rows = rows;
		this.columns = columns;
		
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
	
	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}

	public CollisionType getCollision(int index) {
		return tiles.get(index).getCollision();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getIndex(int x, int y) {
		return tileIds.get(new PointInt2D(x, y));
	}
	
	public SelectedTile getTile(String index) {
		return tiles.get(index);
	}

	public CollisionType getCollision(int column, int row) {
		return tiles.get(getIndex(column, row)).getCollision();
	}
	
	public void createTiles() {
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < columns; i++) {
				String tileId = generateId();
				tileIds.put(new PointInt2D(i, j), tileId);
				
				int x = i*tileWidth;
				int y = j*tileHeight;
				
				tiles.put(tileId, new SelectedTile(tileId, path, x, y, tileWidth, tileHeight, CollisionType.FREE));
			}
		}
	}

	protected String generateId() {
		String tileSetId = this.id; 
		String id = tileSetId+Integer.toHexString(count);
		count++;
		return id;
	}

	public ImageTileFloor getTileFloor(String tileId) {
		SelectedTile tile = getTile(tileId);
		
		ImageTileFloor tileFloor = new ImageTileFloor(path, tileId);
		tileFloor.setLayerBounds(tile.getX(), tile.getY(), tileWidth, tileHeight);
		
		return tileFloor;
	}
	
}