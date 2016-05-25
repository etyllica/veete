package br.com.vite.editor;

import br.com.vite.tile.Tile;

public interface EditorListener {

	void writeTile(Tile lastSelectedTile, String tileSetId, String id);
	void eraseTile(Tile lastSelectedTile);

	
}
