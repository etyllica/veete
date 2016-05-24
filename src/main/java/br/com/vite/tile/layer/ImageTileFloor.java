package br.com.vite.tile.layer;


public class ImageTileFloor extends ImageTileLayer implements DrawableLayer {
					
	public ImageTileFloor(String path, String tileSetId) {
		super(path, tileSetId);
	}
	
	public ImageTileFloor(ImageTileLayer tile) {
		super(tile);		
	}

}
