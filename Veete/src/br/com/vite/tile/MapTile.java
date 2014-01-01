package br.com.vite.tile;

import java.awt.Color;

import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.GeometricLayer;

public class MapTile extends GeometricLayer{

	protected ImageTileLayer layer;
	
	protected ImageTileLayer objectLayer;
	
	private Color color;
	
	public MapTile(int x, int y, int w, int h){
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
	
	public void setObjectLayer(ImageTileLayer layer) {
		this.objectLayer = layer;
	}
	
	public void drawTile(Graphic g){
		if(layer!=null){
			layer.setCoordinates(x, y);
			layer.draw(g);
		}	
	}
	
	public void drawObject(Graphic g){
		if(objectLayer!=null){
			objectLayer.setCoordinates(x+w/2-objectLayer.layer.getW()/2, y-objectLayer.layer.getH()+w/2);
			objectLayer.draw(g);
		}
	}
}
