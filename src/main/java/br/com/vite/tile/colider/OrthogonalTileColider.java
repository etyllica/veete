package br.com.vite.tile.colider;

import br.com.vite.tile.Tile;

public class OrthogonalTileColider extends TileCollider {

	public OrthogonalTileColider(int tileWidth, int tileHeight) {
		super(tileWidth, tileHeight);
	}

	@Override
	public boolean colideTile(Tile tile, int mx, int my, int offsetX, int offsetY) {

		return colideRectangular(tile, mx, my, offsetX, offsetY);
	}

	private boolean colideRectangular(Tile tile, int px, int py, int offsetX, int offsetY) {

		int x = px-tile.getX()-offsetX;
		int y = py-tile.getY()-offsetY;
		
		if((x < tile.getX()) || (x > tile.getX() + tile.getW())) {

			return false;
		}
		
		if((y < tile.getY()) || (y > tile.getY() + tile.getH())) {

			return false;
		}

		return true;
	}

}
