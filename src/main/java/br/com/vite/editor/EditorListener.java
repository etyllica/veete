package br.com.vite.editor;

import br.com.vite.tile.Tile;

public interface EditorListener {

	void writeTile(Tile lastSelectedTile, String id);
	void eraseTile(Tile lastSelectedTile);

	
}
