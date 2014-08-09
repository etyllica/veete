package br.com.vite.tile.filler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.tile.Tile;

public class IsometricTileFiller extends TileFiller {

	private BufferedImage fillCell;
	
	public IsometricTileFiller(int tileSizeX, int tileSizeY) {
		super(tileSizeX, tileSizeY);
		
		createGridCell();
	}
	
	private void createGridCell() {
	
		fillCell = new BufferedImage(tileSizeX, tileSizeY+1, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = fillCell.createGraphics();
		g.setColor(Color.GREEN);		

		Polygon polygon = new Polygon();

		polygon.addPoint(0, tileSizeY/2);
		polygon.addPoint(tileSizeX/2, 0);
		polygon.addPoint(tileSizeX, tileSizeY/2);
		polygon.addPoint(tileSizeX/2, tileSizeY);

		g.fillPolygon(polygon);
	}

	@Override
	public void drawFiller(Tile tile, Graphic g) {

		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		g.setAlpha(45);
		g.drawImage(fillCell, tx, ty);
		g.setAlpha(100);
	}
	
}
