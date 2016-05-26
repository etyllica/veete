package br.com.vite.tile.drawer;

import br.com.etyllica.core.graphics.Graphics;
import br.com.vite.tile.Tile;
import br.com.vite.tile.TileHelper;

public abstract class TileDrawer extends TileHelper {
	
	protected boolean drawGrid = true;
	
	protected boolean drawCollision = true;

	public TileDrawer(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
	}
	
	public void drawTile(Tile tile, Graphics g, int offsetX, int offsetY) {
		
		drawFloor(tile, g, offsetX, offsetY);

		if(drawGrid)
			drawGrid(tile, g, offsetX, offsetY);
				
		drawObject(tile, g, offsetX, offsetY);
		
		if(drawCollision) {
			g.setAlpha(30);
			drawCollision(tile, g, offsetX, offsetY);
			g.setAlpha(100);
		}
	}
	
	protected abstract void drawGrid(Tile tile, Graphics g, int offsetX, int offsetY);
	
	protected abstract void drawCollision(Tile tile, Graphics g, int offsetX, int offsetY);
	
	private void drawFloor(Tile tile, Graphics g, int offsetX, int offsetY) {
		
		if(tile.getLayer() == null)
			return;
	
		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		tile.getLayer().draw(g, tx, ty);		
	}
	
	private void drawObject(Tile tile, Graphics g, int offsetX, int offsetY) {
		
		if(tile.getObjectLayer() == null)
			return;
			
		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;		
		
		tile.getObjectLayer().draw(g, tx, ty, tile.getW(), tile.getH());		
	}

	public boolean isDrawGrid() {
		return drawGrid;
	}

	public void setDrawGrid(boolean drawGrid) {
		this.drawGrid = drawGrid;
	}
	
	public void swapDrawGrid() {
		drawGrid = !drawGrid;
	}
	
	public boolean isDrawCollision() {
		return drawCollision;
	}

	public void setDrawCollision(boolean drawCollision) {
		this.drawCollision = drawCollision;
	}
	
	public void swapDrawCollision() {
		drawCollision = !drawCollision;
	}

}
