package br.com.vite.tile.layer;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class ImageTileFloor extends TileLayer implements DrawableLayer {
	
	protected ImageLayer layer;
			
	public ImageTileFloor(String path) {
		super();
		
		layer = new ImageLayer(path);
	}
	
	public ImageTileFloor(ImageTileFloor tile) {
		super();
		
		String path = tile.layer.getPath();
		int xImage = tile.layer.getXImage();
		int yImage = tile.layer.getYImage();
		int tileWidth = tile.layer.getW();
		int tileHeight = tile.layer.getH();
		
		layer = new ImageLayer(path);
		setLayerBounds(xImage, yImage, tileWidth, tileHeight);
	}
	
	public void setLayerBounds(int xImage, int yImage, int w, int h) {
		layer.setImageCoordinates(xImage, yImage);
		layer.setW(w);
		layer.setH(h);
	}
	
	public void setCoordinates(int x, int y) {
		this.layer.setCoordinates(x, y);
	}
			
	public void draw(Graphic g, int x, int y) {		
		layer.simpleDraw(g, x, y);
	}

	public String getPath() {
		return layer.getPath();
	}
	
	public int getX() {
		return layer.getXImage();
	}
	
	public int getY() {
		return layer.getYImage();
	}

}
