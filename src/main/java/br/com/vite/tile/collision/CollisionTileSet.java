package br.com.vite.tile.collision;

import java.awt.Color;
import java.awt.Graphics2D;

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
		g.fillRect(0, 0, tileWidth, tileHeight);
		
		g.setColor(Color.GREEN);
		g.fillRect(tileWidth, 0, tileWidth, tileHeight);
	}

}
