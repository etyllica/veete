package br.com.vite.tile.collision;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

import br.com.vite.tile.set.ProceduralTileSet;

public class CollisionTileSet extends ProceduralTileSet {

	private Color color = Color.RED;
	
	public CollisionTileSet(int tileWidth, int tileHeight) {
		super(5, 3, tileWidth, tileHeight);
		
		drawTiles();		
	}
	
	private void drawTiles() {

		Graphics2D g = layer.getGraphics();
		
		g.setColor(color);
		g.fillRect(tileWidth, 0, tileWidth, tileHeight);
		
		Polygon polygon = new Polygon();
		polygon.addPoint(tileWidth, tileHeight);
		polygon.addPoint(0, tileHeight*2);
		polygon.addPoint(tileWidth, tileHeight*3);
		polygon.addPoint(tileWidth*2, tileHeight*2);
		
		g.fillPolygon(polygon);		
		
		g.fillRoundRect(tileWidth*2, 0, tileWidth*3, tileHeight*3, tileWidth, tileHeight);
	}

}
