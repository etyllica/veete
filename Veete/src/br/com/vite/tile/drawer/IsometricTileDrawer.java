package br.com.vite.tile.drawer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.tile.Tile;

public class IsometricTileDrawer extends TileDrawer {

	private BufferedImage gridCell;
	
	public IsometricTileDrawer(int tileSizeX, int tileSizeY) {
		super(tileSizeX, tileSizeY);
		
		createGridCell();
	}
	
	private void createGridCell() {
	
		gridCell = new BufferedImage(tileSizeX, tileSizeY+1, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = gridCell.createGraphics();
		g.setColor(Color.BLACK);

		Polygon polygon = new Polygon();

		polygon.addPoint(0, tileSizeY/2);
		polygon.addPoint(tileSizeX/2, 0);
		polygon.addPoint(tileSizeX, tileSizeY/2);
		polygon.addPoint(tileSizeX/2, tileSizeY);

		g.drawPolygon(polygon);
	}

	@Override
	protected void drawFloor(Tile tile, Graphic g) {
		
		if(tile.getLayer() == null)
			return;
	
		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		tile.getLayer().draw(g, tx, ty);
		
	}

	@Override
	protected void drawGrid(Tile tile, Graphic g) {
		
		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		g.drawImage(gridCell, tx, ty);		
	}

	@Override
	protected void drawObject(Tile tile, Graphic g) {
		
		if(tile.getObjectLayer() == null)
			return;
	
		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		tile.getObjectLayer().draw(g, tx, ty);		
		
	}

	
	
}
