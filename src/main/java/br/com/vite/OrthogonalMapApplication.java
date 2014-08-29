package br.com.vite;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.collection.tileset.CastleTileSet;
import br.com.vite.editor.OrthogonalMapEditor;
import br.com.vite.map.selection.OrthogonalSelectionMap;

public class OrthogonalMapApplication extends MapApplication {
	
	final int tileWidth = 16;
	final int tileHeight = 16;

	private int tileSetOffsetY = 300;
	
	private OrthogonalSelectionMap selectionMap;

	public OrthogonalMapApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		final int columns = 48;
		final int lines = 16;

		editor = new OrthogonalMapEditor(columns, lines, tileWidth, tileHeight);
		editor.translateMap(0, 40);

		loading = 30;
		
		selectionMap = new OrthogonalSelectionMap(9, 12, tileWidth, tileHeight);
		selectionMap.translateMap(0, tileSetOffsetY);
		selectionMap.setEditor(editor);
		selectionMap.setTileSet(new CastleTileSet());
		
		loading = 70;
		

		updateAtFixedRate(80);

		loading = 100;
	}

	@Override
	public void timeUpdate(long now) {
		super.timeUpdate(now);
		
		selectionMap.update(now);
	}
		
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		super.updateKeyboard(event);

		return GUIEvent.NONE;
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {				
		super.updateMouse(event);
		selectionMap.updateMouse(event);
		
		return GUIEvent.NONE;
	}

	@Override
	public void draw(Graphic g) {
		super.draw(g);

		selectionMap.draw(g);
	}

}
