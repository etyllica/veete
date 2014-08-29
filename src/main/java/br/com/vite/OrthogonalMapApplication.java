package br.com.vite;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.collection.tileset.CastleTileSet;
import br.com.vite.collection.tileset.LandTileSet;
import br.com.vite.editor.MapEditor;
import br.com.vite.editor.OrthogonalMapEditor;
import br.com.vite.map.selection.OrthogonalSelectionMap;
import br.com.vite.serialization.MapEditorSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OrthogonalMapApplication extends MapApplication {
	
	final int tileWidth = 16;
	final int tileHeight = 16;

	private int tileSetOffsetY = 300;
	
	private OrthogonalSelectionMap selectionCastleMap;
	
	private OrthogonalSelectionMap selectionPlatformMap;

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
		
		selectionCastleMap = new OrthogonalSelectionMap(9, 12, editor);
		selectionCastleMap.translateMap(0, tileSetOffsetY);
		selectionCastleMap.setEditor(editor);
		selectionCastleMap.setTileSet(new CastleTileSet());
		
		selectionPlatformMap = new OrthogonalSelectionMap(10, 3, editor);
		selectionPlatformMap.translateMap(13*tileWidth, tileSetOffsetY);
		selectionPlatformMap.setEditor(editor);
		selectionPlatformMap.setTileSet(new LandTileSet());
		
		loading = 70;		

		updateAtFixedRate(80);

		loading = 100;
	}

	@Override
	public void timeUpdate(long now) {
		super.timeUpdate(now);
		
		selectionCastleMap.update(now);
		
		selectionPlatformMap.update(now);
	}
		
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		super.updateKeyboard(event);

		if(event.isKeyDown(KeyEvent.TSK_1)) {
			final GsonBuilder gsonBuilder = new GsonBuilder();
		    gsonBuilder.registerTypeAdapter(MapEditor.class, new MapEditorSerializer());
		    gsonBuilder.setPrettyPrinting();
		    final Gson gson = gsonBuilder.create();
		    
		    final String json = gson.toJson(editor, MapEditor.class);
		    System.out.println(json);
			
		}
		
		return GUIEvent.NONE;
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {				
		super.updateMouse(event);
		
		selectionCastleMap.updateMouse(event);
		selectionPlatformMap.updateMouse(event);
		
		return GUIEvent.NONE;
	}

	@Override
	public void draw(Graphic g) {
		super.draw(g);

		selectionCastleMap.draw(g);
		selectionPlatformMap.draw(g);
	}

}
