package br.com.vite;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.linear.Point2D;
import br.com.vite.collection.isometric.grassland.floor.Grass;
import br.com.vite.collection.isometric.grassland.floor.Marble;
import br.com.vite.collection.isometric.tree.PalmTree1;
import br.com.vite.map.Map;
import br.com.vite.tile.Tile;
import br.com.vite.tile.colider.IsometricTileColider;
import br.com.vite.tile.drawer.IsometricTileDrawer;
import br.com.vite.tile.filler.IsometricTileFiller;
import br.com.vite.tile.generator.IsometricTileCreator;

public class IsometricMap extends MapApplication {

	private Grass grass;
	private Marble marble;
	private PalmTree1 tree;

	private Point2D target = new Point2D();

	public IsometricMap(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		columns = 13;
		lines = 16;
		tileWidth = 64;
		tileHeight = tileWidth/2;
		
		map = new Map(lines, columns);
		
		map.setCreator(new IsometricTileCreator(tileWidth, tileHeight));
		
		map.setColider(new IsometricTileColider(tileWidth, tileHeight));
		
		map.setDrawer(new IsometricTileDrawer(tileWidth, tileHeight));
		
		map.setFiller(new IsometricTileFiller(tileWidth, tileHeight));
		
		tiles = map.createTiles();

		createImageTiles();

		loading = 10;
				
		lastTile = getTargetTile();
		
		loading = 30;

		translateMap(0, 32);
						
		updateAtFixedRate(80);

		loading = 100;
	}

	private void createImageTiles() {
		grass = new Grass(genereateUniqueId(), 0);
		marble = new Marble(genereateUniqueId(), 0);
		tree = new PalmTree1(genereateUniqueId());

		selectedTile = grass;

		selectedObject = tree;		
	}

	@Override
	public void timeUpdate(long now) {
		super.timeUpdate(now);
		getClicked(mx, my);

		lastTile = getTargetTile();

		if(leftPressed) {
			lastTile.setLayer(selectedTile);
		}else if(rightPressed) {
			lastTile.setObjectLayer(selectedObject);
		}else if(middlePressed) {
			lastTile.setLayer(null);
		}

	}
	
	private Tile getTargetTile() {
		return tiles[(int)target.getY()][(int)target.getX()];
	}

	private Point2D getClicked(int mouseX, int mouseY) {

		int column = (int)(mouseX-map.getOffsetX())/tileWidth;

		int line = (int)(mouseY-map.getOffsetY())/(tileHeight/2);

		if(line<=0) {
			line = 1;
		}else if (line>=lines) {
			line = lines-1;
		}

		if(column<=0) {
			column = 1;
		}else if (column>=columns) {
			column = columns-1;
		}

		for(int j=line-1;j<line+1;j++) {

			for(int i=column-1;i<column+1;i++) {

				if(map.getColider().colideTile(tiles[j][i],mouseX, mouseY, map.getOffsetX(), map.getOffsetY())) {

					target.setLocation(i, j);
				}

			}
		}
		
		return target;

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

}
