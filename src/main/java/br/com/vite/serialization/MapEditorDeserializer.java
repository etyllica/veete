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

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class MapEditorDeserializer implements JsonDeserializer<MapEditor> {
	
	private Map<Integer, String> tileSets = new HashMap<Integer, String>();
	
	private Map<Integer, SelectedTile> tileIds = new HashMap<Integer, SelectedTile>();
       
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
    	
    	JsonArray tileSetsNode = object.getAsJsonArray("tilesets"); 
    	
    	deserializeTileSets(tileSetsNode);
    	//deserialize tiles
    	//deserialize maptiles
    	
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
    		String path = node.get("set").getAsString();
    		
    		tileSets.put(id, path);
    	}    	
    }    
}