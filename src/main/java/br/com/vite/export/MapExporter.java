package br.com.vite.export;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import br.com.etyllica.core.loader.image.ImageLoader;
import br.com.etyllica.util.io.IOHandler;
import br.com.vite.editor.MapEditor;
import br.com.vite.map.Map;
import br.com.vite.serialization.MapEditorDeserializer;
import br.com.vite.serialization.MapEditorSerializer;

public class MapExporter {

	private static final String MAP_FOLDER = "maps/";
	
	public static void export(MapEditor editor, String filename) {
		final Gson gson = createGson();
	    
	    final String json = gson.toJson(editor, MapEditor.class);
	    
	    final String path = getPath(filename);
	    
	    IOHandler.write(path, json);
	}
	
	public static MapEditor load(String filename) throws FileNotFoundException {
		
	    final Gson gson = createGson();
	    
	    final String path = getPath(filename);
	    
	    JsonReader jsonReader = new JsonReader(new FileReader(path));
	    
	    MapEditor editor = gson.fromJson(jsonReader, MapEditor.class);
	    
	    return editor;
	    	    
	}
	
	public static MapEditor reload(MapEditor editor, String filename) throws FileNotFoundException {
		
	    final Gson gson = createGson();
	    
	    final String path = getPath(filename);
	    
	    JsonReader jsonReader = new JsonReader(new FileReader(path));
	    
	    int offsetX = editor.getOffsetX();
		int offsetY = editor.getOffsetY();
	    
	    MapEditor loadedEditor = gson.fromJson(jsonReader, MapEditor.class);
	    
	    loadedEditor.translateMap(offsetX, offsetY);
	    
	    loadedEditor.setObjectTile(editor.getObjectTile());
	    loadedEditor.setFloorTile(editor.getFloorTile());
	    
	    return loadedEditor;
	    	    
	}
	
	public static Map loadMap(String filename) throws FileNotFoundException {
	    final Gson gson = createGson();
	    
	    final String path = getPath(filename);
	    
	    JsonReader jsonReader = new JsonReader(new FileReader(path));
	    
	    MapEditor editor = gson.fromJson(jsonReader, MapEditor.class);
	    	    
	    Map map = editor.getMap();
	    
	    map.getDrawer().setDrawGrid(false);
	    map.getDrawer().setDrawCollision(false);
	    
	    return map;
	}
	
	private static String getPath(String filename) {
		return ImageLoader.getInstance().getPath()+MAP_FOLDER+filename;
	}
	
	private static Gson createGson() {
		final GsonBuilder gsonBuilder = new GsonBuilder();
	    gsonBuilder.registerTypeAdapter(MapEditor.class, new MapEditorSerializer());
	    gsonBuilder.registerTypeAdapter(MapEditor.class, new MapEditorDeserializer());
	    gsonBuilder.setPrettyPrinting();
	    
	    return gsonBuilder.create();
	}
	
}
