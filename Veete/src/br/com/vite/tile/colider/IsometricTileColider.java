package br.com.vite.tile.colider;

import br.com.vite.tile.Tile;

public class IsometricTileColider extends TileColider {

	public IsometricTileColider(int tileSizeX, int tileSizeY) {
		super(tileSizeX, tileSizeY);
	}

	@Override
	public boolean colideTile(Tile tile, int mx, int my) {
		
		return colideIsometric(tile, mx, my);
	}
	
	public boolean colideIsometric(Tile tile, int px, int py) {

		int my = tile.getH()/2;
		int mx = tile.getW()/2;

		int x = px-tile.getX()-offsetX;
		int y = py-tile.getY()-offsetY;

		if(y>+my)
			y=my-(y-my);

		if((x>mx+1+(2*y))||(x<mx-1-(2*y)))
			return false;
		else
			return true;

	}

}
