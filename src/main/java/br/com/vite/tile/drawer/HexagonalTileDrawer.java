package br.com.vite.tile.drawer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.BufferedLayer;
import br.com.vite.tile.Tile;
import br.com.vite.tile.collision.CollisionType;

public class HexagonalTileDrawer extends TileDrawer {

	private BufferedImage gridCell;
	
	private BufferedLayer collisionLayer;
	
	public HexagonalTileDrawer(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
		
		createGridCell();
	}
	
	private void createGridCell() {
	
		gridCell = new BufferedImage(tileWidth, tileHeight+1, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = gridCell.createGraphics();
		g.setColor(Color.BLACK);

		Polygon polygon = new Polygon();

		polygon.addPoint(tileWidth/4, 0);
		polygon.addPoint(tileWidth/2+tileWidth/4, 0);
		polygon.addPoint(tileWidth, tileHeight/2);
		polygon.addPoint(tileWidth/2+tileWidth/4, tileHeight);
		polygon.addPoint(tileWidth/4, tileHeight);
		polygon.addPoint(0, tileHeight/2);
		
		g.drawPolygon(polygon);
		
		collisionLayer = new BufferedLayer(0, 0, tileWidth, tileHeight);
		collisionLayer.getGraphics().setColor(Color.BLACK);
		collisionLayer.getGraphics().fillPolygon(polygon);
	}

	@Override
	protected void drawGrid(Tile tile, Graphics g, int offsetX, int offsetY) {
		
		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		g.drawImage(gridCell, tx, ty);
	}

	@Override
	protected void drawCollision(Tile tile, Graphics g, int offsetX, int offsetY) {

		int tx = tile.getX()+offsetX;
		int ty = tile.getY()+offsetY;
		
		if(tile.getCollision() == CollisionType.FREE) {
			collisionLayer.resetImage();
			collisionLayer.offsetRGB(0, 0xff, 0);
		} else if(tile.getCollision() == CollisionType.BLOCK) {
			collisionLayer.resetImage();
			collisionLayer.offsetRGB(0xff, 0, 0);
		}
		
		collisionLayer.simpleDraw(g, tx, ty);		
	}
	
}
