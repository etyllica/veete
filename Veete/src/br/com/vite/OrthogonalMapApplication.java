package br.com.vite;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.graphics.SVGColor;
import br.com.etyllica.linear.Point2D;
import br.com.vite.collection.isometric.grassland.floor.Grass;
import br.com.vite.collection.isometric.grassland.floor.Marble;
import br.com.vite.collection.orthogonal.gothic.Column;
import br.com.vite.collection.tileset.CastleTileSet;
import br.com.vite.map.Map;
import br.com.vite.map.OrthogonalMap;
import br.com.vite.tile.Tile;
import br.com.vite.tile.colider.OrthogonalTileColider;
import br.com.vite.tile.drawer.OrthogonalTileDrawer;
import br.com.vite.tile.filler.OrthogonalTileFiller;
import br.com.vite.tile.generator.OrthogonalTileCreator;
import br.com.vite.tile.layer.ImageTileFloor;

public class OrthogonalMapApplication extends MapApplication {

	private Grass grass;
	private Marble marble;
	private Column column;

	private Point2D target = new Point2D();

	protected Map selectionMap;

	private CastleTileSet tileSet;

	private int tileSetOffsetY = 300;

	private ImageTileFloor tileSelection;

	private int selectionX = 0;	
	private int selectionY = 0;

	public OrthogonalMapApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		columns = 13;
		lines = 16;

		tileWidth = 16;
		tileHeight = 16;

		map = new OrthogonalMap(lines, columns, tileWidth, tileHeight);

		tiles = map.createTiles();

		createImageTiles();

		createSelectionMap();

		loading = 30;

		translateMap(0, 32);

		updateAtFixedRate(80);

		loading = 100;
	}

	private void createSelectionMap() {

		selectionMap = new OrthogonalMap(10, 10, tileWidth, tileHeight);
		selectionMap.createTiles();

		selectionMap.setOffsetY(tileSetOffsetY);
	}

	private void createImageTiles() {
		grass = new Grass(genereateUniqueId(), 0);
		marble = new Marble(genereateUniqueId(), 0);
		column = new Column(genereateUniqueId());

		tileSet = new CastleTileSet();

		tileSelection = new ImageTileFloor(-1, tileSet.getLayer().getPath());
		tileSelection.setLayerBounds(0, 0, tileWidth, tileHeight);

		selectedTile = tileSelection;

		selectedObject = column;
	}

	@Override
	public void timeUpdate(long now) {
		super.timeUpdate(now);

		Tile lastSelectedTile = map.getTargetTile(mx, my);

		if(leftPressed) {
			
			ImageTileFloor floor = new ImageTileFloor(selectedTile);
						
			lastSelectedTile.setLayer(floor);
			
		} else if(rightPressed) {
			lastSelectedTile.setObjectLayer(selectedObject);
		} else if(middlePressed) {
			lastSelectedTile.setLayer(null);
		}

		if(my>tileSetOffsetY) {

			Tile lastSelectionTile = selectionMap.getTargetTile(mx, my);

			if(leftPressed) {

				int x = lastSelectionTile.getX();
				int y = lastSelectionTile.getY();

				selectedTile.setLayerBounds(x, y, tileWidth, tileHeight);			
			}

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

	@Override
	public void draw(Graphic g) {
		super.draw(g);

		tileSet.getLayer().simpleDraw(g, 0, tileSetOffsetY);

		selectionMap.draw(g, 0, 0);

	}

}
