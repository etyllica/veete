package br.com.vite.tile.drawer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.tile.Tile;

public class IsometricTileDrawer extends TileDrawer {

	private BufferedImage gridCell;
	
	public IsometricTileDrawer(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
		
		createGridCell();
	}
	
	private void createGridCell() {
	
		gridCell = new BufferedImage(tileWidth, tileHeight+1, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = gridCell.createGraphics();
		g.setColor(Color.BLACK);

		Polygon polygon = new Polygon();

		polygon.addPoint(0, tileHeight/2);
		polygon.addPoint(tileWidth/2, 0);
		polygon.addPoint(tileWidth, tileHeight/2);
		polygon.addPoint(tileWidth/2, tileHeight);

		g.drawPolygon(polygon);
	}

	@Override
	protected void drawGrid(Tile tile, Graphic g, int offsetX, int offsetY) {
		
		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		g.drawImage(gridCell, tx, ty);		
	}
	
}
