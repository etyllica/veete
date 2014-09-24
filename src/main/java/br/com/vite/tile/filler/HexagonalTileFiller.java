package br.com.vite.tile.filler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.tile.Tile;

public class HexagonalTileFiller extends TileFiller {

	private BufferedImage fillCell;
	
	public HexagonalTileFiller(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
		
		createGridCell();
	}
	
	@Override
	public void setColor(Color color) {
		super.setColor(color);
		createGridCell();
	}
	
	private void createGridCell() {
	
		fillCell = new BufferedImage(tileWidth, tileHeight+1, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = fillCell.createGraphics();
		g.setColor(color);		

		Polygon polygon = new Polygon();

		polygon.addPoint(tileWidth/4, 0);
		polygon.addPoint(tileWidth/2+tileWidth/4, 0);
		polygon.addPoint(tileWidth, tileHeight/2);
		polygon.addPoint(tileWidth/2+tileWidth/4, tileHeight);
		polygon.addPoint(tileWidth/4, tileHeight);
		polygon.addPoint(0, tileHeight/2);

		g.fillPolygon(polygon);
	}

	@Override
	public void drawTileFiller(Tile tile, Graphic g, int offsetX, int offsetY) {

		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;

		g.drawImage(fillCell, tx, ty);
		
		if(floorTile!=null) {
			floorTile.draw(g, tx, ty);
		}
	}
	
	@Override
	public void drawObjectFiller(Tile tile, Graphic g, int offsetX, int offsetY) {

		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		if(objectTile != null) {
			objectTile.draw(g, tx, ty, tileWidth, tileHeight);
		}
	}
	
}
