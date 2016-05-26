package br.com.vite.map;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.core.linear.PointInt2D;
import br.com.vite.tile.Tile;
import br.com.vite.tile.colider.TileCollider;
import br.com.vite.tile.collision.CollisionType;
import br.com.vite.tile.creator.TileCreator;
import br.com.vite.tile.drawer.TileDrawer;
import br.com.vite.tile.filler.TileFiller;

public abstract class Map {

	protected int x;
	protected int y;
	
	protected int rows;
	protected int columns;

	protected int tileWidth;
	protected int tileHeight;

	protected Tile[][] tiles;

	protected MapType type;

	//Helpers
	protected TileCreator creator;
	protected TileCollider collider;
	protected TileDrawer drawer;
	protected TileFiller filler;

	protected boolean onTarget = false;

	protected PointInt2D target = new PointInt2D();

	public Map(int columns, int lines) {
		super();

		this.rows = lines;
		this.columns = columns;
	}

	public Map(int columns, int rows, int tileWidth, int tileHeight) {
		super();

		this.rows = rows;
		this.columns = columns;

		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}

	public void createTiles() {

		tiles = new Tile[rows][columns];

		for(int j = 0; j < rows; j++) {

			for(int i = 0; i < columns; i++) {

				tiles[j][i] = creator.createTile(j, i);
			}
		}
	}
	
	public Tile getTile(int mouseX, int mouseY) {
		updateTarget(mouseX, mouseY, target);
		return getLastTarget();
	}
	
	public Tile getTile(int mouseX, int mouseY, PointInt2D target) {
		updateTarget(mouseX, mouseY, target);

		return getLastTarget();
	}

	public Tile updateTarget(int mouseX, int mouseY) {
		return getTile(mouseX, mouseY);
	}

	public abstract void getIndex(int mouseX, int mouseY, PointInt2D out);
	public abstract boolean updateTarget(int mouseX, int mouseY, PointInt2D target);

	public Tile[][] getTiles() {
		return tiles;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setLocation(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public TileCreator getCreator() {
		return creator;
	}

	public void setCreator(TileCreator creator) {
		this.creator = creator;
	}

	public TileCollider getCollider() {
		return collider;
	}

	public void setColider(TileCollider colider) {
		this.collider = colider;
	}

	public TileDrawer getDrawer() {
		return drawer;
	}

	public void setDrawer(TileDrawer drawer) {
		this.drawer = drawer;
	}

	public TileFiller getFiller() {
		return filler;
	}

	public void setFiller(TileFiller filler) {
		this.filler = filler;
	}

	public void draw(Graphics g) {
		drawRect(g, 0, 0);
	}
	
	/**
	 * Draw with offset
	 * @param g
	 * @param x - offset in x axis
	 * @param y - offset in y axis
	 */
	public void draw(Graphics g, int x, int y) {
		draw(g, 0, 0, columns, rows, x, y);
	}

	public void drawRect(Graphics g, int x, int y) {
		draw(g, x, y, columns, rows, 0, 0);
	}

	public void draw(Graphics g, int x, int y, int columns, int lines, int offsetX, int offsetY) {

		for(int j = y; j < lines; j++) {

			for(int i = x; i < columns; i++) {

				Tile tile = tiles[j][i];

				drawer.drawTile(tile, g, this.x+offsetX, this.y+offsetY);
			}
		}
	}

	public void drawTileFiller(Graphics g) {
		drawTileFiller(g, getLastTarget());
	}

	public void drawTileFiller(Graphics g, Tile target) {
		g.setAlpha(50);
		filler.drawTileFiller(target, g, x, y);
		g.setAlpha(100);
	}

	public void drawObjectFiller(Graphics g) {
		drawObjectFiller(g, getLastTarget());
	}

	public void drawObjectFiller(Graphics g, Tile target) {
		g.setAlpha(50);
		filler.drawObjectFiller(target, g, x, y);
		g.setAlpha(100);
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public MapType getType() {
		return type;
	}

	public boolean isOnTarget() {
		return onTarget;
	}

	public Tile getLastTarget() {
		return tiles[target.getY()][target.getX()];
	}

	public boolean isBlock(Tile tile) {
		CollisionType collision = tile.getCollision();
		return collision == CollisionType.BLOCK;
	}
	
	public boolean isBlock(PointInt2D point) {
		return isBlock(point.getX(), point.getY());
	}
	
	public boolean isBlock(int x, int y) {
		Tile tile = tiles[y][x];
		CollisionType collision = tile.getCollision();

		return collision == CollisionType.BLOCK;
	}

	public boolean isPlatform(Tile tile) {

		CollisionType collision = tile.getCollision();

		boolean isFixed = collision == CollisionType.UPPER || 
				collision == CollisionType.UPPER_RIGHT || 
				collision == CollisionType.UPPER_LEFT ||
				collision == CollisionType.BLOCK;

		return isFixed;
	}
}
