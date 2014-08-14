package br.com.vite.map;

import br.com.vite.tile.Tile;
import br.com.vite.tile.generator.TileCreator;

public class Map {
	
	private int lines;
	
	private int columns;
		
	public Map(int lines, int columns) {
		super();
		
		this.lines = lines;
		
		this.columns = columns;
	}
		
	public Tile[][] createTiles(TileCreator creator) {
				
		Tile[][] tiles = new Tile[lines][columns]; 
		
		for(int j = 0; j < lines; j++) {

			for(int i = 0; i < columns; i++) {
		
				tiles[j][i] = creator.createTile(j, i);
			}
		}
		
		return tiles;
	}
	
}
