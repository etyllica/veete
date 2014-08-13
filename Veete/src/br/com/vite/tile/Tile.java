package br.com.vite.tile;

import java.awt.Color;

import br.com.etyllica.layer.GeometricLayer;
import br.com.vite.tile.layer.ImageTileFloor;
import br.com.vite.tile.layer.ImageTileObject;

public class Tile extends GeometricLayer {

	protected ImageTileFloor layer;
	
	protected ImageTileObject objectLayer;
	
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

	public ImageTileFloor getLayer() {
		return layer;
	}

	public void setLayer(ImageTileFloor layer) {
		this.layer = layer;
	}
		
	public ImageTileObject getObjectLayer() {
		return objectLayer;
	}

	public void setObjectLayer(ImageTileObject layer) {
		this.objectLayer = layer;
	}
	
}
