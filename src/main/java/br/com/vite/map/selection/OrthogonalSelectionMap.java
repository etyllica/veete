package br.com.vite.map.selection;

import java.util.HashMap;
import java.util.Map;

import br.com.etyllica.core.graphics.Graphic;
import br.com.vite.editor.MapEditor;
import br.com.vite.editor.OrthogonalMapEditor;
import br.com.vite.tile.Tile;
import br.com.vite.tile.layer.ImageTileFloor;
import br.com.vite.tile.set.TileSet;

public class OrthogonalSelectionMap extends OrthogonalMapEditor {

	private MapEditor editor;
	
	private TileSet tileSet;
	
	private Map<SelectedTile, ImageTileFloor> selectedTiles = new HashMap<SelectedTile, ImageTileFloor>();
	
	public OrthogonalSelectionMap(int columns, int lines, MapEditor editor) {
		super(columns, lines, editor.getTileWidth(), editor.getTileHeight());
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
				
				int tileWidth = editor.getTileWidth();
				int tileHeight = editor.getTileHeight();
				
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
			tileFloor.setLayerBounds(x, y, editor.getTileWidth(), editor.getTileHeight());
			
			selectedTiles.put(selectedTile, tileFloor);
			
			return tileFloor;
		}
		
		return floor;
	}
	
	public void draw(Graphic g) {
		tileSet.getLayer().simpleDraw(g, map.getOffsetX(), map.getOffsetY());
		
		map.draw(g, 0, 0);
	}

	public MapEditor getEditor() {
		return editor;
	}

	public void setEditor(MapEditor editor) {
		this.editor = editor;
	}

	public TileSet getTileSet() {
		return tileSet;
	}

	public void setTileSet(TileSet tileSet) {
		this.tileSet = tileSet;
	}

}
