package br.com.vite.map.selection;

import java.util.HashMap;
import java.util.Map;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.editor.OrthogonalMapEditor;
import br.com.vite.tile.Tile;
import br.com.vite.tile.layer.ImageTileFloor;
import br.com.vite.tile.set.TileSet;

public class OrthogonalSelectionMap extends OrthogonalMapEditor {
	
	private TileSet tileSet;
	
	private SelectionMapListener listener;
	
	private Map<SelectedTile, ImageTileFloor> selectedTiles = new HashMap<SelectedTile, ImageTileFloor>();
	
	public OrthogonalSelectionMap(int columns, int lines, int tileWidth, int tileHeight) {
		super(columns, lines, tileWidth, tileHeight);
	}

	public Map<SelectedTile, ImageTileFloor> getSelectedTiles() {
		return selectedTiles;
	}

	public void setSelectedTiles(Map<SelectedTile, ImageTileFloor> selectedTiles) {
		this.selectedTiles = selectedTiles;
	}
	
	@Override
	public void update(long now) {
		
		Tile lastSelectionTile = map.getTargetTile(mx, my);
		
		if(map.isOnMouse()) {
			
			if(leftPressed) {

				int x = lastSelectionTile.getX();
				int y = lastSelectionTile.getY();
				
				int tileWidth = map.getTileWidth();
				int tileHeight = map.getTileHeight();
				
				ImageTileFloor selectedTile = createSelectedTile(tileSet.getLayer().getPath(), x, y, tileWidth, tileHeight);

				notifySelectedFloorTile(selectedTile);
			}
		}
	}
	
	private void notifySelectedFloorTile(ImageTileFloor selectedTile) {
		if(listener == null)
			return;
		
		listener.setFloorTile(selectedTile);
	}
	
	private ImageTileFloor createSelectedTile(String path, int x, int y, int width, int height) {
		
		SelectedTile selectedTile = new SelectedTile(path, x, y, width, height);
		
		ImageTileFloor floor = selectedTiles.get(selectedTile);
		
		if(floor == null) {
		
			ImageTileFloor tileFloor = new ImageTileFloor(path);
			tileFloor.setLayerBounds(x, y, map.getTileWidth(), map.getTileHeight());
			
			selectedTiles.put(selectedTile, tileFloor);
			
			return tileFloor;
		}
		
		return floor;
	}
	
	@Override
	public void draw(Graphic g) {
		tileSet.getLayer().simpleDraw(g, map.getOffsetX(), map.getOffsetY());
		
		map.draw(g, 0, 0);
	}
	
	public void setListener(SelectionMapListener listener) {
		this.listener = listener;
	}

	public TileSet getTileSet() {
		return tileSet;
	}

	public void setTileSet(TileSet tileSet) {
		this.tileSet = tileSet;
	}

}
