package br.com.vite.serialization;

import java.lang.reflect.Type;

import br.com.vite.editor.MapEditor;
import br.com.vite.tile.Tile;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class MapEditorSerializer implements JsonSerializer<MapEditor> {

	@Override
	public JsonElement serialize(MapEditor editor, Type type,
			JsonSerializationContext context) {

		final int lines = editor.getLines();
		final int columns = editor.getColumns();
		
		JsonObject element = new JsonObject();
		
		element.add("columns", context.serialize(columns));
		element.add("lines", context.serialize(lines));		
		element.add("tile_width", context.serialize(editor.getTileWidth()));
		element.add("tile_height", context.serialize(editor.getTileHeight()));
		
		JsonArray array = serializeTileArray(editor.getTiles());

		//Set<String> tilesets = new HashSet<String>();
		
		element.add("tiles", array);

		return element;
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
				
				tileNode.addProperty("tileSet", tile.getLayer().getPath());
				tileNode.addProperty("x", i);
				tileNode.addProperty("y", j);
				tileNode.addProperty("xImage", tile.getLayer().getX());
				tileNode.addProperty("yImage", tile.getLayer().getY());
				
				array.add(tileNode);
			}
		}
		
		return array;
	}

}
