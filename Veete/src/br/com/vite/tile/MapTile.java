package br.com.vite.tile;

import java.awt.Color;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.Layer;

public class MapTile extends Layer implements Drawable{

	private ImageTileLayer layer;
	
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
	
	public void draw(Graphic g){
		if(layer!=null){
			layer.setCoordinates(x, y);
			layer.draw(g);
		}
	}
	
}
