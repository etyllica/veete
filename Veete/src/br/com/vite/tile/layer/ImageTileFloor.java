package br.com.vite.tile.layer;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class ImageTileFloor extends TileLayer implements DrawableLayer {
	
	protected ImageLayer layer;
	
	private String path;
	
	private int xImage = 0;
	private int yImage = 0;
	private int tileWidth = 0;
	private int tileHeight = 0;
		
	public ImageTileFloor(long uniqueId, String path) {
		super(uniqueId);
		
		layer = new ImageLayer(path);
		
		this.path = path;
	}
	
	public ImageTileFloor(ImageTileFloor tile) {
		super();
		
		this.path = tile.layer.getPath();
		this.xImage = tile.layer.getXImage();
		this.yImage = tile.layer.getYImage();
		this.tileWidth = tile.layer.getW();
		this.tileHeight = tile.layer.getH();
		
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

}
