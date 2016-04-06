package examples;

import br.com.etyllica.core.event.KeyEvent;
import br.com.vite.MapApplication;
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
						
		updateAtFixedRate(80, this);

		loading = 100;
	}

	private void createImageTiles() {
				
		grass = new Grass(0);
		marble = new Marble(0);
		tree = new PalmTree1();

		editor.setFloorTile(grass);

		selectedObject = tree;		
	}

	@Override
	public void timeUpdate(long now) {
		super.timeUpdate(now);

		editor.update(now);
	}
	
	@Override
	public void updateKeyboard(KeyEvent event) {
		super.updateKeyboard(event);
		
		if(event.isKeyDown(KeyEvent.VK_1)) {
			editor.setFloorTile(grass);
		}

		if(event.isKeyDown(KeyEvent.VK_2)) {
			editor.setFloorTile(marble);
		}
	}

}
