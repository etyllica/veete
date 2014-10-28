package br.com.vite.serialization;

import java.util.Map;

import com.google.gson.JsonObject;

public interface TileDeserializer<T> {

	public T deserialize(Map<Integer, String> tilesets, JsonObject node);
	
}
