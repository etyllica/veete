package br.com.vite.tile.layer;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class ImageTileFloor extends TileLayer implements DrawableLayer {
	
	protected ImageLayer layer;
		
	public ImageTileFloor(long uniqueId, String path) {
		super(uniqueId);
		
		layer = new ImageLayer(path);
	}
	
	public ImageTileFloor(ImageTileFloor tile) {
		super();
		
		layer = new ImageLayer(tile.layer.getPath());
		setLayerBounds(tile.layer.getXImage(), tile.layer.getYImage(), tile.layer.getW(), tile.layer.getH());
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

}
