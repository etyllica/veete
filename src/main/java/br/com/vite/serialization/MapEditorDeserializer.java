package br.com.vite.serialization;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import br.com.vite.editor.HexagonalMapEditor;
import br.com.vite.editor.IsometricMapEditor;
import br.com.vite.editor.MapEditor;
import br.com.vite.editor.OrthogonalMapEditor;
import br.com.vite.map.MapType;
import br.com.vite.map.selection.SelectedTile;
import br.com.vite.tile.layer.ImageTileFloor;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class MapEditorDeserializer implements JsonDeserializer<MapEditor> {
	
	private Map<Integer, String> tileSets = new HashMap<Integer, String>();
	
	private Map<Integer, SelectedTile> tileIds = new HashMap<Integer, SelectedTile>();
	
	private Map<SelectedTile, ImageTileFloor> selectedTiles = new HashMap<SelectedTile, ImageTileFloor>();
       
    @Override
	public MapEditor deserialize(JsonElement element, Type type,
			JsonDeserializationContext context) throws JsonParseException {
    	
    	JsonObject object = element.getAsJsonObject();
    	
    	int columns = object.get(MapEditorSerializer.JSON_COLUMNS).getAsInt();
    	int lines = object.get(MapEditorSerializer.JSON_LINES).getAsInt();
    	int tileWidth = object.get(MapEditorSerializer.JSON_TILE_WIDTH).getAsInt();
    	int tileHeight = object.get(MapEditorSerializer.JSON_TILE_HEIGHT).getAsInt();
    	
    	MapType mapType = context.deserialize(object.get(MapEditorSerializer.JSON_TYPE), MapType.class);
    	
    	MapEditor editor = createMap(columns, lines, tileWidth, tileHeight, mapType);
    	
    	JsonArray tileSetsNode = object.getAsJsonArray(MapEditorSerializer.JSON_TILESETS);
    	deserializeTileSets(tileSetsNode);
    	
    	JsonArray tilesNode = object.getAsJsonArray(MapEditorSerializer.JSON_TILES);
    	deserializeTiles(tilesNode, tileWidth, tileHeight);
    	
    	JsonArray mapNode = object.getAsJsonArray(MapEditorSerializer.JSON_MAP);
    	deserializeMap(editor, mapNode);
    	
		return editor;
	}
        
    private MapEditor createMap(int columns, int lines, int tileWidth, int tileHeight, MapType type) {
    	
    	switch(type) {
    	default:
    	case ORTHOGONAL:
    		return new OrthogonalMapEditor(columns, lines, tileWidth, tileHeight);
    	case ISOMETRIC:
    		return new IsometricMapEditor(columns, lines, tileWidth, tileHeight);
    	case HEXAGONAL:
    		return new HexagonalMapEditor(columns, lines, tileWidth, tileHeight);
    	}    	
    }
    
    private void deserializeTileSets(JsonArray array) {
    
    	for(int i = 0; i < array.size(); i++) {
    		JsonObject node = array.get(i).getAsJsonObject();
    		
    		int id = node.get("id").getAsInt();
    		String path = node.get("path").getAsString();
    		
    		tileSets.put(id, path);
    	}
    }
    
    private void deserializeTiles(JsonArray array, int tileWidth, int tileHeight) {
    	    	
    	for(int i = 0; i < array.size(); i++) {
    		JsonObject node = array.get(i).getAsJsonObject();
    		
    		int setId = node.get("set").getAsInt();
    		
    		String path = tileSets.get(setId);
    		
            int x = node.get("xImage").getAsInt();
            int y = node.get("yImage").getAsInt();
            
            SelectedTile tile = new SelectedTile(path, x, y, tileWidth, tileHeight);
    		
            int id = node.get("id").getAsInt();
            
    		tileIds.put(id, tile);
    	}
    }
    
    private void deserializeMap(MapEditor editor, JsonArray array) {
    	
    	for(int i = 0; i < array.size(); i++) {
    		JsonObject node = array.get(i).getAsJsonObject();
    		    		    		
            int x = node.get("x").getAsInt();
            int y = node.get("y").getAsInt();
            int id = node.get("id").getAsInt();            
    		
            editor.getTiles()[y][x].setLayer(createSelectedTile(tileIds.get(id)));
    	}
    }
    
    private ImageTileFloor createSelectedTile(SelectedTile selectedTile) {
				
		ImageTileFloor floor = selectedTiles.get(selectedTile);
		
		if(floor == null) {
		
			ImageTileFloor tileFloor = new ImageTileFloor(selectedTile.getPath());
			tileFloor.setLayerBounds(selectedTile.getX(), selectedTile.getY(), selectedTile.getWidth(), selectedTile.getHeight());
			
			selectedTiles.put(selectedTile, tileFloor);
			
			return tileFloor;
		}
		
		return floor;
	}
    
}