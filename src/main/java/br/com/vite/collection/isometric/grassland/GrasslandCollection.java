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
import br.com.vite.map.MapType;
import br.com.vite.tile.layer.ImageTileFloor;
import br.com.vite.tile.set.TileSet;

public class GrasslandCollection extends TileSet implements TileCollection, ObjectCollection{
	
	private List<ImageTileFloor> tiles;

	private List<ImageTileFloor> objects;

	public GrasslandCollection(String id) {
		super(64, 32, MapType.ISOMETRIC);
		this.id = id;
		
		tiles = new ArrayList<ImageTileFloor>();
		
		loadTiles();

		objects = new ArrayList<ImageTileFloor>();
		
		loadObjects();

	}
	
	private void loadTiles(){
		
		//Load Grass
		for(int i = 0; i < 16; i++) {
			tiles.add(new Grass(id, i));
		}

		//Load Grass with Bricks
		for(int i = 0; i < 16; i++) {
			tiles.add(new GrassWithBricks(id, i));	
		}

		//Load Marble
		for(int i = 0; i < 16; i++) {
			tiles.add(new Marble(id, i));
		}
	}
	
	private void loadObjects(){
		
		//Graves
		objects.add(new GraveNE(id));
		
		objects.add(new GraveNW(id));
		
		//Crates
		objects.add(new CrateClosedNW(id));
		objects.add(new CrateOpenedNW(id));
		objects.add(new CrateClosedNE(id));
		objects.add(new CrateOpenedNE(id));
		
		objects.add(new FirewoodNW(id));
		objects.add(new FirewoodNE(id));
		objects.add(new Fireplace(id));
		objects.add(new Anvil(id));
	}

	@Override
	public List<ImageTileFloor> getTiles() {
		return tiles;
	}

	@Override
	public List<ImageTileFloor> getObjects() {
		return objects;
	}
	
}
