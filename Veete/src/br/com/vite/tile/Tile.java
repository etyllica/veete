package br.com.vite.tile;

import java.awt.Color;

import br.com.etyllica.layer.GeometricLayer;

public class Tile extends GeometricLayer {

	protected ImageTileLayer layer;
	
	protected ImageTileLayer objectLayer;
	
	private Color color;
	
	public Tile(int x, int y, int w, int h){
		super(x,y,w,h);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ImageTileLayer getLayer() {
		return layer;
	}

	public void setLayer(ImageTileLayer layer) {
		this.layer = layer;
	}
		
	public ImageTileLayer getObjectLayer() {
		return objectLayer;
	}

	public void setObjectLayer(ImageTileLayer layer) {
		this.objectLayer = layer;
	}
	
}
