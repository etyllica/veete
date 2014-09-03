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
		for(int i = 0; i < 16; i++) {
			tiles.add(new Grass(i));	
		}

		//Load Grass with Bricks
		for(int i = 0; i < 16; i++) {
			tiles.add(new GrassWithBricks(i));	
		}

		//Load Marble
		for(int i = 0; i < 16; i++) {
			tiles.add(new Marble(i));
		}
	}
	
	private void loadObjects(){
		
		//Graves
		objects.add(new GraveNE());
		
		objects.add(new GraveNW());
		
		//Crates
		objects.add(new CrateClosedNW());
		objects.add(new CrateOpenedNW());
		objects.add(new CrateClosedNE());
		objects.add(new CrateOpenedNE());
		
		objects.add(new FirewoodNW());
		objects.add(new FirewoodNE());	
		objects.add(new Fireplace());
		objects.add(new Anvil());
		
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
	
}
