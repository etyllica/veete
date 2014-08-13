package br.com.vite.tile.layer;

import br.com.etyllica.core.graphics.Graphic;

public interface DrawableObject {

	public void draw(Graphic g, int x, int y, int tileWidth, int tileHeight);
	
}
