package examples;

import java.io.FileNotFoundException;

import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.vite.MapApplication;
import br.com.vite.collection.tileset.CastleTileSet;
import br.com.vite.collection.tileset.land.BigGrass;
import br.com.vite.collection.tileset.land.LandTileSet;
import br.com.vite.editor.OrthogonalMapEditor;
import br.com.vite.export.MapExporter;
import br.com.vite.map.selection.OrthogonalCollisionMap;
import br.com.vite.map.selection.OrthogonalFloorSelection;
import br.com.vite.network.listener.MapProtocolListener;
import br.com.vite.ui.Toolbar;

public class CollaborativeMapApplication extends MapApplication {

	private final String mapFile = "map.json";
	private static final String ID_CASTLE = "castle";
	private static final String ID_LAND = "land";

	private final int tileWidth = 16;
	private final int tileHeight = 16;

	private int tileSetOffsetY = 300;

	private OrthogonalFloorSelection selectionCastleMap;

	private OrthogonalFloorSelection selectionPlatformMap;

	private OrthogonalCollisionMap selectionCollisionMap;

	private BigGrass smallGrass;

	Toolbar bar;

	public CollaborativeMapApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		final int columns = 50;
		final int lines = 16;

		editor = new OrthogonalMapEditor(columns, lines, tileWidth, tileHeight);
		editor.translateMap(0, 110);
		
		loading = 30;

		selectionCollisionMap = new OrthogonalCollisionMap(tileWidth, tileHeight);
		selectionCollisionMap.translateMap(28*tileWidth, tileSetOffsetY);

		loading = 50;
		CastleTileSet castle = new CastleTileSet(ID_CASTLE);
		editor.addTileSet(castle);

		selectionCastleMap = new OrthogonalFloorSelection(tileWidth, tileHeight, castle);
		selectionCastleMap.translateMap(0, tileSetOffsetY);
		selectionCastleMap.setListener(editor);
		selectionCastleMap.setCollisionMap(selectionCollisionMap);

		loading = 60;
		LandTileSet land = new LandTileSet(ID_LAND);
		editor.addTileSet(land);

		selectionPlatformMap = new OrthogonalFloorSelection(tileWidth, tileHeight, land);
		selectionPlatformMap.translateMap(13*tileWidth, tileSetOffsetY);
		selectionPlatformMap.setListener(editor);
		selectionPlatformMap.setCollisionMap(selectionCollisionMap);

		loading = 70;

		smallGrass = new BigGrass(land.getId());
		editor.setObjectTile(smallGrass);
		
		try {
			reloadMap();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		bar = new Toolbar(this, editor);
		
		//Remove
		for (int i = 0;i < 22; i++) {
			editor.writeFloorTile(i, 0, ID_CASTLE, "castle1");
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
	public void draw(Graphics g) {
		super.draw(g);

		selectionCastleMap.draw(g);
		selectionPlatformMap.draw(g);
		selectionCollisionMap.draw(g);
	}

}
