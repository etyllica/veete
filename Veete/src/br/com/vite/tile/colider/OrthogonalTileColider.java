package br.com.vite.tile.colider;

import br.com.vite.tile.Tile;

public class OrthogonalTileColider extends TileColider {

	public OrthogonalTileColider(int tileSizeX, int tileSizeY) {
		super(tileSizeX, tileSizeY);
	}

	@Override
	public boolean colideTile(Tile tile, int mx, int my) {

		return colideRectangular(tile, mx, my);
	}

	public boolean colideRectangular(Tile tile, int px, int py) {

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
