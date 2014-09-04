package br.com.vite.serialization;

import java.lang.reflect.Type;

import br.com.vite.editor.MapEditor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.vite.map.selection.SelectedTile;
import br.com.vite.tile.Tile;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class MapEditorSerializer implements JsonSerializer<MapEditor> {

	public static final String JSON_VERSION = "version";
	public static final String JSON_TYPE = "type";
	public static final String JSON_COLUMNS = "columns";
	public static final String JSON_LINES = "lines";
	public static final String JSON_TILE_WIDTH = "tile_width";
	public static final String JSON_TILE_HEIGHT = "tile_height";
	
	public static final String JSON_TILESETS = "tilesets";
	public static final String JSON_TILES = "tiles";
	
	private static final int MAP_VERSION = 1;
	
    private int uniqueId = 0;
    
    private int tileSetId = 0;
   
    private Map<SelectedTile, Integer> uniqueIds;
    
    private Map<String, Integer> tileSets;
   
    @Override
    public JsonElement serialize(MapEditor editor, Type type,
            JsonSerializationContext context) {

        final int lines = editor.getLines();
        final int columns = editor.getColumns();
       
        JsonObject element = new JsonObject();
       
        element.add(JSON_VERSION, new JsonPrimitive(MAP_VERSION));
        element.add(JSON_TYPE, context.serialize(editor.getType()));
        element.add(JSON_COLUMNS, context.serialize(columns));
        element.add(JSON_LINES, context.serialize(lines));
        element.add(JSON_TILE_WIDTH, context.serialize(editor.getTileWidth()));
        element.add(JSON_TILE_HEIGHT, context.serialize(editor.getTileHeight()));
       
        tileSets = new HashMap<String, Integer>();
        
        uniqueIds = new HashMap<SelectedTile, Integer>();        
       
        JsonArray array = serializeTileArray(editor.getTiles());
       
        element.add(JSON_TILESETS, serializeTileSets());
        
        element.add(JSON_TILES, serializeuniqueIds());
       
        element.add("map", array);

        return element;
    }
   
    private JsonArray serializeTileSets() {
        
        JsonArray array = new JsonArray();
        
        for(Entry<String, Integer> entry: tileSets.entrySet()) {
        	
        	JsonObject tilesetNode = new JsonObject();
        	
        	tilesetNode.addProperty("id", entry.getValue());
        	tilesetNode.addProperty("path", entry.getKey());
        	
        	array.add(tilesetNode);
        }
        
        return array;
    }
    
    private JsonArray serializeuniqueIds() {
       
        JsonArray array = new JsonArray();
       
        for(Entry<SelectedTile, Integer> entry: uniqueIds.entrySet()) {
           
            JsonObject tileNode = new JsonObject();
           
            tileNode.addProperty("id", entry.getValue());
            tileNode.addProperty("set", tileSets.get(entry.getKey().getPath()));
            tileNode.addProperty("xImage", entry.getKey().getX());
            tileNode.addProperty("yImage", entry.getKey().getY());
           
            array.add(tileNode);
        }
       
        return array;
    }   
   
    private JsonArray serializeTileArray(Tile[][] tiles) {
       
        int columns = tiles[0].length;
        int lines = tiles.length;
       
        JsonArray array = new JsonArray();

        for(int j = 0; j < lines; j++) {
           
            for(int i = 0; i < columns; i++) {
               
                Tile tile = tiles[j][i];
               
                if(tile.getLayer() == null)
                    continue;
                               
                JsonObject tileNode = new JsonObject();
               
                SelectedTile selection = new SelectedTile(tile.getLayer().getPath(), tile.getLayer().getX(), tile.getLayer().getY());
               
                tileNode.addProperty("x", i);
                tileNode.addProperty("y", j);
                tileNode.addProperty("id", getUniqueId(selection));
                               
                array.add(tileNode);
            }
        }
       
        return array;
    }
   
    private int getUniqueId(SelectedTile selectedTile) {
       
        if(!uniqueIds.containsKey(selectedTile)) {
            uniqueIds.put(selectedTile, uniqueId);
            uniqueId++;
            
            generateTileSetId(selectedTile.getPath());            
        }
       
        return uniqueIds.get(selectedTile);
    }
    
    private void generateTileSetId(String path) {
    	
    	if(!tileSets.containsKey(path)) {
        	tileSets.put(path, tileSetId);
        	tileSetId++;
        }    	
    }

}