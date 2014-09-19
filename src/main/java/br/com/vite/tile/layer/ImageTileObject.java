package br.com.vite.tile.layer;

import br.com.etyllica.core.graphics.Graphic;

public class ImageTileObject extends ImageTileLayer implements DrawableObject {
	
	protected String label = "";
		
	protected int offsetX = 0;
	
	protected int offsetY = 0;
	
	public ImageTileObject(String path) {
		super(path);		
	}
		
	public void draw(Graphic g, int x, int y, int tileWidth, int tileHeight) {
		layer.simpleDraw(g, offsetX+x-tileWidth/2, offsetY+y-layer.getH()+tileHeight);
	}
	
	public int getW() {
		return layer.getW();
	}
	
	public int getH() {
		return layer.getH();
	}

}
