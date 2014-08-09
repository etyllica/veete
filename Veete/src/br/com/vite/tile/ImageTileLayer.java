package br.com.vite.tile;

import br.com.etyllica.core.graphics.Graphic;
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
	
	public void setCoordinates(int x, int y){
		this.layer.setCoordinates(x, y);
	}
	
	public void draw(Graphic g){		
		layer.simpleDraw(g);
	}
	
	public void draw(Graphic g, int x, int y){		
		layer.simpleDraw(g, x, y);
	}

}
