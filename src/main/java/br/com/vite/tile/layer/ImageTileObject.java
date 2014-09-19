package br.com.vite.tile.layer;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class ImageTileObject extends TileLayer implements DrawableObject {
	
	protected String label = "";
	
	protected ImageLayer layer;
	
	protected int offsetX = 0;
	
	protected int offsetY = 0;
	
	public ImageTileObject(String path) {
		super();
		
		layer = new ImageLayer(path);
	}
	
	public void setLayerBounds(int xImage, int yImage, int w, int h) {
		layer.setImageCoordinates(xImage, yImage);
		layer.setW(w);
		layer.setH(h);
	}
	
	public void setCoordinates(int x, int y) {
		this.layer.setCoordinates(x, y);
	}
		
	public void draw(Graphic g, int x, int y, int tileWidth, int tileHeight) {
		layer.simpleDraw(g, offsetX+x-tileWidth/2, offsetY+y-layer.getH()+tileHeight);
	}

}
