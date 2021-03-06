package br.com.vite.tile.layer;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.ImageLayer;


public class ImageTileLayer extends TileLayer {
	
	protected ImageLayer layer;
		
	public ImageTileLayer(String path, String tileSetId) {
		super(tileSetId);
		
		layer = new ImageLayer(path);
	}
	
	public ImageTileLayer(ImageTileLayer tile) {
		super();
		
		copy(tile);
		
	}
	
	public void copy(ImageTileLayer tile) {
		String path = tile.layer.getPath();
		int xImage = tile.layer.getXImage();
		int yImage = tile.layer.getYImage();
		int tileWidth = tile.layer.getW();
		int tileHeight = tile.layer.getH();
		
		this.collision = tile.getCollision();
		this.id = tile.id;
		this.tileSetId = tile.tileSetId;
		
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
			
	public void draw(Graphics g, int x, int y) {		
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
