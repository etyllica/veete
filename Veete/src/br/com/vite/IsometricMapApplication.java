package br.com.vite;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.vite.collection.isometric.grassland.floor.Grass;
import br.com.vite.collection.isometric.grassland.floor.Marble;
import br.com.vite.collection.isometric.tree.PalmTree1;
import br.com.vite.editor.IsometricMapEditor;

public class IsometricMapApplication extends MapApplication {

	private Grass grass;
	private Marble marble;
	private PalmTree1 tree;
		
	private final int tileWidth = 64;
	private final int tileHeight = tileWidth/2;

	public IsometricMapApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		final int columns = 13;
		final int lines = 16;
				
		editor = new IsometricMapEditor(columns, lines, tileWidth, tileHeight);

		createImageTiles();

		loading = 10;
		
		loading = 30;

		editor.translateMap(0, 32);
						
		updateAtFixedRate(80);

		loading = 100;
	}

	private void createImageTiles() {
		
		final int uniqueId = 0;
		
		grass = new Grass(uniqueId, 0);
		marble = new Marble(uniqueId+1, 0);
		tree = new PalmTree1(uniqueId+2);

		editor.setFloorTile(grass);

		selectedObject = tree;		
	}

	@Override
	public void timeUpdate(long now) {
		super.timeUpdate(now);

		editor.update(now);		
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		super.updateKeyboard(event);
		
		if(event.isKeyDown(KeyEvent.TSK_1)) {
			editor.setFloorTile(grass);
		}

		if(event.isKeyDown(KeyEvent.TSK_2)) {
			editor.setFloorTile(marble);
		}

		return GUIEvent.NONE;
	}

}
