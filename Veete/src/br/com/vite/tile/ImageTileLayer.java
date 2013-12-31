package br.com.vite.tile;

import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class ImageTileLayer extends TileLayer{
	
	protected ImageLayer layer;
	
	public ImageTileLayer(long uniqueId, String path){
		super(uniqueId);
		
		layer = new ImageLayer(path);		
	}
	
	public void setLayerBounds(int xImage, int yImage, int w, int h){
		layer.setImageCoordinates(xImage, yImage);
		layer.setW(w);
		layer.setH(h);
	}
	
	public void setCoordinates(float x, float y){
		this.layer.setCoordinates(x, y);
	}
	
	public void draw(Graphic g){		
		layer.draw(g);
	}

}
