package br.com.vite;

import java.util.HashMap;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.collection.isometric.grassland.floor.Grass;
import br.com.vite.collection.isometric.grassland.floor.Marble;
import br.com.vite.collection.orthogonal.gothic.Column;
import br.com.vite.collection.tileset.CastleTileSet;
import br.com.vite.map.Map;
import br.com.vite.map.OrthogonalMap;
import br.com.vite.map.selection.SelectedTile;
import br.com.vite.tile.Tile;
import br.com.vite.tile.layer.ImageTileFloor;

public class OrthogonalMapApplication extends MapApplication {

	private Grass grass;
	private Marble marble;
	private Column column;

	protected Map selectionMap;

	private CastleTileSet tileSet;

	private int tileSetOffsetY = 300;
	
	private java.util.Map<SelectedTile, ImageTileFloor> selectedTiles = new HashMap<SelectedTile, ImageTileFloor>();

	public OrthogonalMapApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		columns = 48;
		lines = 16;

		tileWidth = 16;
		tileHeight = 16;

		map = new OrthogonalMap(lines, columns, tileWidth, tileHeight);

		tiles = map.createTiles();

		createImageTiles();

		createSelectionMap();

		loading = 30;

		translateMap(0, 40);

		updateAtFixedRate(80);

		loading = 100;
	}

	private void createSelectionMap() {

		selectionMap = new OrthogonalMap(12, 9, tileWidth, tileHeight);
		selectionMap.createTiles();

		selectionMap.setOffsetY(tileSetOffsetY);
	}

	private void createImageTiles() {
		grass = new Grass(genereateUniqueId(), 0);
		marble = new Marble(genereateUniqueId(), 0);
		column = new Column(genereateUniqueId());

		tileSet = new CastleTileSet();

		selectedObject = column;
	}

	@Override
	public void timeUpdate(long now) {
		super.timeUpdate(now);

		Tile lastSelectedTile = map.getTargetTile(mx, my);

		if(map.isOnMouse()) {

			if(leftPressed) {

				lastSelectedTile.setLayer(selectedTile);

			} else if(rightPressed) {
				lastSelectedTile.setObjectLayer(selectedObject);
			} else if(middlePressed) {
				lastSelectedTile.setLayer(null);
			}

		}

		Tile lastSelectionTile = selectionMap.getTargetTile(mx, my);
		
		if(selectionMap.isOnMouse()) {

			if(leftPressed) {

				int x = lastSelectionTile.getX();
				int y = lastSelectionTile.getY();
				
				selectedTile = getSelectedTile(tileSet.getLayer().getPath(), x, y, tileWidth, tileHeight);

				map.getFiller().setFloorTile(selectedTile);
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

		if(event.isKeyDown(KeyEvent.TSK_1)) {
			selectedTile = grass;
		}

		if(event.isKeyDown(KeyEvent.TSK_2)) {
			selectedTile = marble;
		}

		return GUIEvent.NONE;
	}

	@Override
	public void draw(Graphic g) {
		super.draw(g);

		tileSet.getLayer().simpleDraw(g, 0, tileSetOffsetY);

		selectionMap.draw(g, 0, 0);
	}

}
