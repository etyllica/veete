package br.com.vite.map;

import br.com.etyllica.core.graphics.Graphic;
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
	
	protected int lines;
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

		this.lines = lines;
		this.columns = columns;
	}

	public Map(int columns, int lines, int tileWidth, int tileHeight) {
		super();

		this.lines = lines;
		this.columns = columns;

		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}

	public void createTiles() {

		tiles = new Tile[lines][columns]; 

		for(int j = 0; j < lines; j++) {

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

	public void draw(Graphic g) {
		drawRect(g, 0, 0);
	}
	
	/**
	 * Draw with offset
	 * @param g
	 * @param x - offset in x axis
	 * @param y - offset in y axis
	 */
	public void draw(Graphic g, int x, int y) {
		draw(g, 0, 0, columns, lines, x, y);
	}

	public void drawRect(Graphic g, int x, int y) {
		draw(g, x, y, columns, lines, 0, 0);
	}

	public void draw(Graphic g, int x, int y, int columns, int lines, int offsetX, int offsetY) {

		for(int j = y; j < lines; j++) {

			for(int i = x; i < columns; i++) {

				Tile tile = tiles[j][i];

				drawer.drawTile(tile, g, this.x+offsetX, this.y+offsetY);
			}
		}
	}

	public void drawTileFiller(Graphic g) {
		drawTileFiller(g, getLastTarget());
	}

	public void drawTileFiller(Graphic g, Tile target) {
		g.setAlpha(50);
		filler.drawTileFiller(target, g, x, y);
		g.setAlpha(100);
	}

	public void drawObjectFiller(Graphic g) {
		drawObjectFiller(g, getLastTarget());
	}

	public void drawObjectFiller(Graphic g, Tile target) {
		g.setAlpha(50);
		filler.drawObjectFiller(target, g, x, y);
		g.setAlpha(100);
	}

	public int getLines() {
		return lines;
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

	public boolean isPlatform(Tile tile) {

		CollisionType collision = tile.getCollision();

		boolean isFixed = collision == CollisionType.UPPER || 
				collision == CollisionType.UPPER_RIGHT || 
				collision == CollisionType.UPPER_LEFT ||
				collision == CollisionType.BLOCK;

		return isFixed;
	}
}
