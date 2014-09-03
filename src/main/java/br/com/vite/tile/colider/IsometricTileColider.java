package br.com.vite.tile.colider;

import br.com.vite.tile.Tile;

public class IsometricTileColider extends TileColider {

	public IsometricTileColider(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
	}

	@Override
	public boolean colideTile(Tile tile, int mx, int my, int offsetX, int offsetY) {
		
		return colideIsometric(tile, mx, my, offsetX, offsetY);
	}
	
	private boolean colideIsometric(Tile tile, int px, int py, int offsetX, int offsetY) {

		int x = px-tile.getX()-offsetX;
		int y = py-tile.getY()-offsetY;
		
		int my = tile.getH()/2;
		int mx = tile.getW()/2;

		if(y > my)
			y = my-(y-my);

		if((x > mx+1+(2*y)) || (x < mx-1-(2*y)))
			return false;
		
		return true;

	}

}
