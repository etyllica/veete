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
import br.com.vite.tile.Tile;
import br.com.vite.tile.colider.OrthogonalTileColider;
import br.com.vite.tile.drawer.OrthogonalTileDrawer;
import br.com.vite.tile.filler.OrthogonalTileFiller;
import br.com.vite.tile.generator.OrthogonalTileCreator;
import br.com.vite.tile.layer.ImageTileFloor;

public class OrthogonalMap extends MapApplication {

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

	public OrthogonalMap(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		columns = 13;
		lines = 16;

		tileWidth = 16;
		tileHeight = 16;
		
		map = new Map(lines, columns);
		
		map.setCreator(new OrthogonalTileCreator(tileWidth, tileHeight));

		map.setColider(new OrthogonalTileColider(tileWidth, tileHeight));

		map.setDrawer(new OrthogonalTileDrawer(tileWidth, tileHeight));

		map.setFiller(new OrthogonalTileFiller(tileWidth, tileHeight));

		tiles = map.createTiles();

		createImageTiles();
		
		createSelectionMap();

		loading = 10;

		lastTile = getTargetTile();

		loading = 30;

		translateMap(0, 32);

		updateAtFixedRate(80);

		loading = 100;
	}
	
	private void createSelectionMap() {
		
		selectionMap = new Map(10, 10);
		selectionMap.setCreator(map.getCreator());
		selectionMap.setDrawer(map.getDrawer());
		selectionMap.createTiles();
		
		selectionMap.setOffsetY(tileSetOffsetY);				
	}

	private void createImageTiles() {
		grass = new Grass(genereateUniqueId(), 0);
		marble = new Marble(genereateUniqueId(), 0);
		column = new Column(genereateUniqueId());

		tileSet = new CastleTileSet();

		tileSelection = new ImageTileFloor(-1, tileSet.getLayer().getPath());		
		tileSelection.setLayerBounds(0, 300-tileSetOffsetY, tileWidth, tileHeight);

		selectedTile = tileSelection;

		selectedObject = column;
	}

	@Override
	public void timeUpdate(long now) {
		super.timeUpdate(now);

		if(my<tileSetOffsetY) {

			getClicked(mx, my);

			lastTile = getTargetTile();

			if(leftPressed) {
				lastTile.setLayer(selectedTile);
			}else if(rightPressed) {
				lastTile.setObjectLayer(selectedObject);
			}else if(middlePressed) {
				lastTile.setLayer(null);
			}

		} else {
			
		}

	}

	private Tile getTargetTile() {
		return tiles[(int)target.getY()][(int)target.getX()];
	}

	private Point2D getClicked(int mouseX, int mouseY) {

		int column = (int)((mouseX-map.getOffsetX())/tileWidth);

		int line = (int)((mouseY-map.getOffsetY())/tileHeight);

		if(line < 0)
			line = 0;
		else if(line >= tiles.length)
			line = tiles.length-1; 

		if(column < 0)
			column = 0;
		else if(column >= tiles[0].length)
			column = tiles[0].length-1;

		int j = line;
		int i = column;

		target.setLocation(i, j);

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

	@Override
	public void draw(Graphic g) {
		super.draw(g);
		
		tileSet.getLayer().simpleDraw(g, 0, tileSetOffsetY);
		
		selectionMap.draw(g, 0, 0);
		
		//Draw selectionFiller
		g.setColor(SVGColor.BLUE);
		
		g.setAlpha(45);
		g.fillRect(selectionX*tileWidth, tileSetOffsetY+selectionY*tileHeight, tileWidth, tileHeight);
		g.setAlpha(100);
		
	}

}
