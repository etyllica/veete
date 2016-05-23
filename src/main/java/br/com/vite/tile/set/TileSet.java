package br.com.vite.tile.set;

import java.util.HashMap;

import br.com.etyllica.core.linear.PointInt2D;
import br.com.vite.map.MapType;
import br.com.vite.map.selection.SelectedTile;
import br.com.vite.tile.collision.CollisionType;

public class TileSet {
	
	private int count = 0;
	protected String id;
	
	protected int tileWidth = 64;
	protected int tileHeight = 64;
	
	protected int columns = 0;
	protected int rows = 0;
	
	private MapType type = MapType.ORTHOGONAL;
	
	protected CollisionType[][] collision;
	
	protected java.util.Map<PointInt2D, String> tileIds = new HashMap<PointInt2D, String>();
	protected java.util.Map<String, SelectedTile> tiles = new HashMap<String, SelectedTile>();
	
	public TileSet(int tileWidth, int tileHeight) {
		super();
		
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}
	
	public TileSet(int tileWidth, int tileHeight, MapType type) {
		this(tileWidth, tileHeight);
		this.type = type;
	}
	
	public TileSet(int tileWidth, int tileHeight, int columns, int rows, MapType type) {
		this(tileWidth, tileHeight);
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

	public CollisionType[][] getCollision() {
		return collision;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getIndex(int x, int y) {
		return tileIds.get(new PointInt2D(x, y));
	}
	
	public SelectedTile getTile(String index) {
		return tiles.get(index);
	}
	

	protected String generateId() {
		String tileSetId = this.id; 
		String id = tileSetId+Integer.toHexString(count);
		count++;
		return id;
	}
	
}