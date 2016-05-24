package br.com.vite.network.listener;

public interface MapProtocolListener {
	void writeFloorTile(String peerId, int x, int y, String tileSetId, String tileId);
	void eraseFloorTile(String peerId, int x, int y);
	void writeObjectTile(String peerId, int x, int y, String tileSetId, String tileId);
	void eraseObjectTile(String peerId, int x, int y);
}
