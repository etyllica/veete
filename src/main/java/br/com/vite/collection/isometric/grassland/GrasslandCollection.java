package br.com.vite.collection.isometric.grassland;

import java.util.ArrayList;
import java.util.List;

import br.com.vite.collection.ObjectCollection;
import br.com.vite.collection.TileCollection;
import br.com.vite.collection.isometric.grassland.floor.Grass;
import br.com.vite.collection.isometric.grassland.floor.GrassWithBricks;
import br.com.vite.collection.isometric.grassland.floor.Marble;
import br.com.vite.collection.isometric.grassland.objects.Anvil;
import br.com.vite.collection.isometric.grassland.objects.GraveNE;
import br.com.vite.collection.isometric.grassland.objects.GraveNW;
import br.com.vite.collection.isometric.grassland.objects.crate.CrateClosedNE;
import br.com.vite.collection.isometric.grassland.objects.crate.CrateClosedNW;
import br.com.vite.collection.isometric.grassland.objects.crate.CrateOpenedNE;
import br.com.vite.collection.isometric.grassland.objects.crate.CrateOpenedNW;
import br.com.vite.collection.isometric.grassland.objects.wood.Fireplace;
import br.com.vite.collection.isometric.grassland.objects.wood.FirewoodNE;
import br.com.vite.collection.isometric.grassland.objects.wood.FirewoodNW;
import br.com.vite.tile.layer.ImageTileFloor;

public class GrasslandCollection implements TileCollection, ObjectCollection{

	private int uniqueId = 0;

	private List<ImageTileFloor> tiles;

	private List<ImageTileFloor> objects;

	public GrasslandCollection(){
		super();

		tiles = new ArrayList<ImageTileFloor>();
		
		loadTiles();

		objects = new ArrayList<ImageTileFloor>();
		
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
		
		//Graves
		objects.add(new GraveNE(genereateUniqueId()));
		
		objects.add(new GraveNW(genereateUniqueId()));
		
		//Crates
		objects.add(new CrateClosedNW(genereateUniqueId()));
		objects.add(new CrateOpenedNW(genereateUniqueId()));
		objects.add(new CrateClosedNE(genereateUniqueId()));
		objects.add(new CrateOpenedNE(genereateUniqueId()));
		
		objects.add(new FirewoodNW(genereateUniqueId()));
		objects.add(new FirewoodNE(genereateUniqueId()));	
		objects.add(new Fireplace(genereateUniqueId()));
		objects.add(new Anvil(genereateUniqueId()));
		
	}

	private int genereateUniqueId(){
		return uniqueId++;
	}

	@Override
	public List<ImageTileFloor> getTiles() {

		return tiles;
	}

	@Override
	public List<ImageTileFloor> getObjects() {
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
