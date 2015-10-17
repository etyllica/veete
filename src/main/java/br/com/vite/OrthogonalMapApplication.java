package br.com.vite;

import java.io.FileNotFoundException;

import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.collection.tileset.CastleTileSet;
import br.com.vite.collection.tileset.land.BigGrass;
import br.com.vite.collection.tileset.land.LandTileSet;
import br.com.vite.editor.OrthogonalMapEditor;
import br.com.vite.export.MapExporter;
import br.com.vite.map.selection.OrthogonalCollisionMap;
import br.com.vite.map.selection.OrthogonalFloorSelection;

public class OrthogonalMapApplication extends MapApplication {
	
	private final String mapFile = "map.json";
	
	private final int tileWidth = 16;
	private final int tileHeight = 16;

	private int tileSetOffsetY = 300;
	
	private OrthogonalFloorSelection selectionCastleMap;
	
	private OrthogonalFloorSelection selectionPlatformMap;
	
	private OrthogonalCollisionMap selectionCollisionMap;
	
	private BigGrass smallGrass;
	
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

		selectionCollisionMap = new OrthogonalCollisionMap(tileWidth, tileHeight);
		selectionCollisionMap.translateMap(28*tileWidth, tileSetOffsetY);
		
		loading = 50;
		selectionCastleMap = new OrthogonalFloorSelection(tileWidth, tileHeight, new CastleTileSet());
		selectionCastleMap.translateMap(0, tileSetOffsetY);
		selectionCastleMap.setListener(editor);
		selectionCastleMap.setCollisionMap(selectionCollisionMap);		
		
		loading = 60;
		selectionPlatformMap = new OrthogonalFloorSelection(tileWidth, tileHeight, new LandTileSet());
		selectionPlatformMap.translateMap(13*tileWidth, tileSetOffsetY);
		selectionPlatformMap.setListener(editor);
		selectionPlatformMap.setCollisionMap(selectionCollisionMap);
		
		loading = 70;

		smallGrass = new BigGrass();
		editor.setObjectTile(smallGrass);
		
		try {
			reloadMap();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		updateAtFixedRate(80, this);
		
		loading = 100;
	}

	@Override
	public void timeUpdate(long now) {
		super.timeUpdate(now);
		
		selectionCastleMap.update(now);
		
		selectionPlatformMap.update(now);
		
		selectionCollisionMap.update(now);
	}
		
	@Override
	public void updateKeyboard(KeyEvent event) {
		super.updateKeyboard(event);

		if(event.isKeyDown(KeyEvent.VK_1)) {
					    
		    MapExporter.export(editor, mapFile);
		}
		
		if(event.isKeyDown(KeyEvent.VK_2)) {
		    
			try {
				reloadMap();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
	}
	
	private void reloadMap() throws FileNotFoundException {
		int offsetX = editor.getOffsetX();
		int offsetY = editor.getOffsetY();
		
		editor = MapExporter.load(mapFile);
		selectionCastleMap.setListener(editor);
		selectionPlatformMap.setListener(editor);
										
		editor.translateMap(offsetX, offsetY);
	}
		
	@Override
	public void updateMouse(PointerEvent event) {	
		selectionCastleMap.updateMouse(event);
		selectionPlatformMap.updateMouse(event);
		selectionCollisionMap.updateMouse(event);
		
		super.updateMouse(event);
	}

	@Override
	public void draw(Graphic g) {
		super.draw(g);
		
		selectionCastleMap.draw(g);
		selectionPlatformMap.draw(g);
		selectionCollisionMap.draw(g);
	}

}
