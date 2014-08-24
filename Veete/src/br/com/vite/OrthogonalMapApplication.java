package br.com.vite;

import java.util.HashMap;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.collection.tileset.CastleTileSet;
import br.com.vite.editor.OrthogonalMapEditor;
import br.com.vite.map.Map;
import br.com.vite.map.OrthogonalMap;
import br.com.vite.map.selection.SelectedTile;
import br.com.vite.tile.Tile;
import br.com.vite.tile.layer.ImageTileFloor;

public class OrthogonalMapApplication extends MapApplication {
	
	final int tileWidth = 16;
	final int tileHeight = 16;

	protected Map selectionMap;

	private CastleTileSet tileSet;

	private int tileSetOffsetY = 300;
	
	private java.util.Map<SelectedTile, ImageTileFloor> selectedTiles = new HashMap<SelectedTile, ImageTileFloor>();

	public OrthogonalMapApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		final int columns = 48;
		final int lines = 16;

		editor = new OrthogonalMapEditor(columns, lines, tileWidth, tileHeight);

		createTileSets();

		createSelectionMap();

		loading = 30;

		editor.translateMap(0, 40);

		updateAtFixedRate(80);

		loading = 100;
	}

	private void createSelectionMap() {

		selectionMap = new OrthogonalMap(9, 12, tileWidth, tileHeight);
		selectionMap.createTiles();

		selectionMap.setOffsetY(tileSetOffsetY);
	}

	private void createTileSets() {
		
		tileSet = new CastleTileSet();
	}

	@Override
	public void timeUpdate(long now) {
		super.timeUpdate(now);

		editor.update(now);
		
		Tile lastSelectionTile = selectionMap.getTargetTile(mx, my);
		
		if(selectionMap.isOnMouse()) {

			if(leftPressed) {

				int x = lastSelectionTile.getX();
				int y = lastSelectionTile.getY();
				
				ImageTileFloor selectedTile = getSelectedTile(tileSet.getLayer().getPath(), x, y, tileWidth, tileHeight);

				editor.setFloorTile(selectedTile);
			}
		}
	}
		
	private ImageTileFloor getSelectedTile(String path, int x, int y, int width, int height) {
		
		SelectedTile selectedTile = new SelectedTile(path, x, y, width, height);
		
		ImageTileFloor floor = selectedTiles.get(selectedTile);
		
		if(floor == null) {
		
			ImageTileFloor tileFloor = new ImageTileFloor(-1, path);
			tileFloor.setLayerBounds(x, y, tileWidth, tileHeight);
			
			selectedTiles.put(selectedTile, tileFloor);
			
			return tileFloor;
		}
		
		return floor;
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		super.updateKeyboard(event);

		return GUIEvent.NONE;
	}

	@Override
	public void draw(Graphic g) {
		super.draw(g);

		tileSet.getLayer().simpleDraw(g, 0, tileSetOffsetY);

		selectionMap.draw(g, 0, 0);
	}

}
