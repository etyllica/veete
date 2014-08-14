package br.com.vite;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.vite.collection.isometric.grassland.floor.Grass;
import br.com.vite.collection.isometric.grassland.floor.Marble;
import br.com.vite.collection.isometric.tree.PalmTree1;
import br.com.vite.map.IsometricMap;
import br.com.vite.tile.Tile;

public class IsometricMapApplication extends MapApplication {

	private Grass grass;
	private Marble marble;
	private PalmTree1 tree;

	public IsometricMapApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		columns = 13;
		lines = 16;
		tileWidth = 64;
		tileHeight = tileWidth/2;
		
		map = new IsometricMap(lines, columns, tileWidth, tileHeight);
				
		tiles = map.createTiles();

		createImageTiles();

		loading = 10;
		
		loading = 30;

		translateMap(0, 32);
						
		updateAtFixedRate(80);

		loading = 100;
	}

	private void createImageTiles() {
		grass = new Grass(genereateUniqueId(), 0);
		marble = new Marble(genereateUniqueId(), 0);
		tree = new PalmTree1(genereateUniqueId());

		selectedTile = grass;

		selectedObject = tree;		
	}

	@Override
	public void timeUpdate(long now) {
		super.timeUpdate(now);

		Tile lastSelectedTile = map.getTargetTile(mx, my);

		if(leftPressed) {
			lastSelectedTile.setLayer(selectedTile);
		}else if(rightPressed) {
			lastSelectedTile.setObjectLayer(selectedObject);
		}else if(middlePressed) {
			lastSelectedTile.setLayer(null);
		}

	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		super.updateKeyboard(event);
		
		if(event.isKeyDown(KeyEvent.TSK_1)) {
			selectedTile = grass;
		}

		if(event.isKeyDown(KeyEvent.TSK_2)) {
			selectedTile = marble;
		}

		return GUIEvent.NONE;
	}

}
