package br.com.vite.tile.filler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.tile.Tile;

public class IsometricTileFiller extends TileFiller {

	private BufferedImage fillCell;
	
	public IsometricTileFiller(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
		
		createGridCell();
	}
	
	private void createGridCell() {
	
		fillCell = new BufferedImage(tileWidth, tileHeight+1, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = fillCell.createGraphics();
		g.setColor(Color.GREEN);		

		Polygon polygon = new Polygon();

		polygon.addPoint(0, tileHeight/2);
		polygon.addPoint(tileWidth/2, 0);
		polygon.addPoint(tileWidth, tileHeight/2);
		polygon.addPoint(tileWidth/2, tileHeight);

		g.fillPolygon(polygon);
	}

	@Override
	public void drawFiller(Tile tile, Graphic g, int offsetX, int offsetY) {

		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;

		g.drawImage(fillCell, tx, ty);
	}
	
}
