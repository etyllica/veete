package br.com.vite.tile;

import br.com.etyllica.core.video.Graphic;


public class ObjectTile extends MapTile{
		
	public ObjectTile(int x, int y, int tileSize){
		super(x,y,tileSize,tileSize/2);
	}
	
	@Override
	public void draw(Graphic g){
				
		if(layer!=null){
			layer.setCoordinates(x+w/2-layer.layer.getW()/2, y-layer.layer.getH()+w/2);
			layer.draw(g);
		}
	}

}
