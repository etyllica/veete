package br.com.vite.tile.colider;

import br.com.vite.tile.Tile;

public class HexagonalTileColider extends TileColider {

	public HexagonalTileColider(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
	}

	@Override
	public boolean colideTile(Tile tile, int mx, int my, int offsetX, int offsetY) {
		
		return colideHexagonal(tile, mx, my, offsetX, offsetY);
	}
	
	private boolean colideHexagonal(Tile tile, int px, int py, int offsetX, int offsetY) {

		int mx = tile.getW()/4;
		int my = tile.getH()/2;
		
		int x = px-tile.getX()-offsetX;
		int y = py-tile.getY()-offsetY;
		
		if(x > mx*3) {
			x = mx-(x-mx*3);
		} else if(x > mx) {
			return py >= tile.getY() && py <= tile.getY()+tile.getH();
		}
		
		if((y > my + 1 + (2*x))||(y<my - 1 - (2*x)))
			return false;
		
		return true;

	}

}
