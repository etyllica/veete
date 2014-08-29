package br.com.vite.serialization;

import java.lang.reflect.Type;

import br.com.vite.editor.MapEditor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class MapEditorSerializer implements JsonSerializer<MapEditor> {

	@Override
	public JsonElement serialize(MapEditor editor, Type type,
			JsonSerializationContext context) {

		JsonObject element = new JsonObject();
		
		element.add("columns", context.serialize(editor.getColumns()));
		element.add("lines", context.serialize(editor.getLines()));		
		element.add("tile_width", context.serialize(editor.getTileWidth()));
		element.add("tile_height", context.serialize(editor.getTileHeight()));		
		
		return element;
	}

}
