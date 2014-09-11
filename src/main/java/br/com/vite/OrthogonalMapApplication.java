package br.com.vite;

import java.io.FileNotFoundException;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.collection.tileset.CastleTileSet;
import br.com.vite.collection.tileset.LandTileSet;
import br.com.vite.editor.OrthogonalMapEditor;
import br.com.vite.export.MapExporter;
import br.com.vite.map.selection.OrthogonalCollisionMap;
import br.com.vite.map.selection.OrthogonalSelectionMap;
import br.com.vite.tile.collision.CollisionTileSet;

public class OrthogonalMapApplication extends MapApplication {
	
	private final String mapFile = "map.json";
	
	private final int tileWidth = 16;
	private final int tileHeight = 16;

	private int tileSetOffsetY = 300;
	
	private OrthogonalSelectionMap selectionCastleMap;
	
	private OrthogonalSelectionMap selectionPlatformMap;
	
	private OrthogonalCollisionMap selectionCollisionMap;
	
	public OrthogonalMapApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		final int columns = 50;
		final int lines = 16;

		editor = new OrthogonalMapEditor(columns, lines, tileWidth, tileHeight);
		editor.translateMap(0, 40);

		loading = 30;
		
		selectionCastleMap = new OrthogonalSelectionMap(9, 12, tileWidth, tileHeight);
		selectionCastleMap.translateMap(0, tileSetOffsetY);
		selectionCastleMap.setListener(editor);
		selectionCastleMap.setTileSet(new CastleTileSet());
		
		selectionPlatformMap = new OrthogonalSelectionMap(10, 8, tileWidth, tileHeight);
		selectionPlatformMap.translateMap(13*tileWidth, tileSetOffsetY);
		selectionPlatformMap.setListener(editor);
		selectionPlatformMap.setTileSet(new LandTileSet());
		
		selectionCollisionMap = new OrthogonalCollisionMap(5, 3, tileWidth, tileHeight);
		selectionCollisionMap.setListener(editor);
		selectionCollisionMap.setTileSet(new CollisionTileSet(tileWidth, tileHeight));
		selectionCollisionMap.translateMap(28*tileWidth, tileSetOffsetY);
		
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
					    
		    MapExporter.export(editor, mapFile);
		}
		
		if(event.isKeyDown(KeyEvent.TSK_2)) {
		    
			try {
				
				int offsetX = editor.getOffsetX();
				int offsetY = editor.getOffsetY();
				
				editor = MapExporter.load(mapFile);
				selectionCastleMap.setListener(editor);
				selectionPlatformMap.setListener(editor);
				selectionCollisionMap.setListener(editor);
								
				editor.translateMap(offsetX, offsetY);
								
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		
		return GUIEvent.NONE;
	}
		
	@Override
	public GUIEvent updateMouse(PointerEvent event) {				
		super.updateMouse(event);
		
		selectionCastleMap.updateMouse(event);
		selectionPlatformMap.updateMouse(event);
		selectionCollisionMap.updateMouse(event);
		
		return GUIEvent.NONE;
	}

	@Override
	public void draw(Graphic g) {
		super.draw(g);
		
		selectionCastleMap.draw(g);
		selectionPlatformMap.draw(g);
		selectionCollisionMap.draw(g);
	}

}
