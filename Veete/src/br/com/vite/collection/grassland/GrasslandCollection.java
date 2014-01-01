package br.com.vite.collection.grassland;

import java.util.ArrayList;
import java.util.List;

import br.com.vite.collection.ObjectCollection;
import br.com.vite.collection.TileCollection;
import br.com.vite.collection.grassland.floor.Grass;
import br.com.vite.collection.grassland.floor.GrassWithBricks;
import br.com.vite.collection.grassland.floor.Marble;
import br.com.vite.collection.grassland.objects.GraveNE;
import br.com.vite.collection.grassland.objects.GraveNW;
import br.com.vite.tile.ImageTileLayer;

public class GrasslandCollection implements TileCollection, ObjectCollection{

	private int uniqueId = 0;

	private List<ImageTileLayer> tiles;

	private List<ImageTileLayer> objects;

	public GrasslandCollection(){
		super();

		tiles = new ArrayList<ImageTileLayer>();
		
		loadTiles();

		objects = new ArrayList<ImageTileLayer>();
		
		loadObjects();

	}
	
	private void loadTiles(){
		
		//Load Grass
		for(int i=0;i<16;i++){
			tiles.add(new Grass(genereateUniqueId(), i));	
		}

		//Load Grass with Bricks
		for(int i=0;i<16;i++){
			tiles.add(new GrassWithBricks(genereateUniqueId(), i));	
		}

		//Load Marble
		for(int i=0;i<16;i++){
			tiles.add(new Marble(genereateUniqueId(), i));
		}
	}
	
	private void loadObjects(){
		
		objects.add(new GraveNE(genereateUniqueId()));
		
		objects.add(new GraveNW(genereateUniqueId()));
		
	}

	private int genereateUniqueId(){
		return uniqueId++;
	}

	@Override
	public List<ImageTileLayer> getTiles() {

		return tiles;
	}

	@Override
	public List<ImageTileLayer> getObjects() {
		// TODO Auto-generated method stub
		return objects;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

}
