package br.com.vite.tile.layer;

import br.com.etyllica.core.graphics.Graphic;

public class ImageTileObject extends ImageTileLayer implements DrawableObject { 
	
	protected String label = "";
	protected int offsetX = 0;
	protected int offsetY = 0;
	
	public ImageTileObject(String path, String tileSetId) {
		super(path, tileSetId);
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
	
}
