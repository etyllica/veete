package br.com.vite.network.client;

import br.com.midnight.model.Peer;
import br.com.midnight.protocol.common.StringClientProtocol;
import br.com.vite.editor.MapEditor;

public class MapClientProtocol extends StringClientProtocol {

	private MapEditor editor;

	public static final String PREFIX_VEETE = "vt";

	public static final String PREFIX_ADD_FLOOR_TILE = "a";
	public static final String PREFIX_REMOVE_FLOOR_TILE = "r";

	public MapClientProtocol(MapEditor editor) {
		super(PREFIX_VEETE);
		this.editor = editor;
	}

	@Override
	public void receiveTCP(Peer peer, String msg) {

		System.out.println(this.getClass().getSimpleName()+"(TCP) received "+msg);
		
		if (msg.startsWith(PREFIX_ADD_FLOOR_TILE)) {
			String[] parts = msg.split(" ");
			String peerId = parts[1];
			String tileSetId = parts[2];
			String tileId = parts[3];
			int i = Integer.parseInt(parts[4]);
			int j = Integer.parseInt(parts[5]);
			
			editor.writeFloorTile(i, j, tileSetId, tileId);
			//editor.eraseFloorTile(x, y);
		} else if (msg.startsWith(PREFIX_REMOVE_FLOOR_TILE)) {
			String[] parts = msg.split(" ");
			String peerId = parts[1];
			int i = Integer.parseInt(parts[2]);
			int j = Integer.parseInt(parts[3]);
			
			editor.eraseFloorTile(i, j);
		}
		
	}

	@Override
	public void receiveUDP(Peer peer, String msg) {
		System.out.println(this.getClass().getSimpleName()+"(UDP) received "+msg);
	}

	public void sendWriteTile(int i, int j, String tileSetId, String id) {
		sendTCP(PREFIX_ADD_FLOOR_TILE+" "+tileSetId+" "+id+" "+i+" "+j);
	}

	public void sendEraseTile(int i, int j) {
		sendTCP(PREFIX_REMOVE_FLOOR_TILE+" "+i+" "+j);
	}

}
