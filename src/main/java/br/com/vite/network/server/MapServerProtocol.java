package br.com.vite.network.server;

import br.com.midnight.model.Peer;
import br.com.midnight.protocol.common.StringServerProtocol;
import br.com.vite.network.client.MapClientProtocol;

public class MapServerProtocol extends StringServerProtocol {
		
	public MapServerProtocol(String prefix) {
		super(prefix);
	}
	
	@Override
	public void receiveUDP(Peer peer, String msg) {
		// TODO Auto-generated method stub
	}

	@Override
	public void receiveTCP(Peer peer, String msg) {
		
		System.out.println(getClass().getSimpleName()+" - Received TCP: "+msg);		
		
		if (msg.startsWith(MapClientProtocol.PREFIX_ADD_FLOOR_TILE)) {
			String[] parts = msg.split(" ");
			String tileSetId = parts[1];
			String tileId = parts[2];
			int i = Integer.parseInt(parts[3]);
			int j = Integer.parseInt(parts[4]);
			
			sendTCPtoAll(MapClientProtocol.PREFIX_ADD_FLOOR_TILE+" "+peer.getSessionID()+" "+tileSetId+" "+tileId+" "+i+" "+j);
		} else if (msg.startsWith(MapClientProtocol.PREFIX_REMOVE_FLOOR_TILE)) {
			String[] parts = msg.split(" ");
			
			int i = Integer.parseInt(parts[1]);
			int j = Integer.parseInt(parts[2]);
			
			sendTCPtoAll(MapClientProtocol.PREFIX_REMOVE_FLOOR_TILE+" "+peer.getSessionID()+" "+i+" "+j);
		} else {
			sendTCPtoAll("Hallu "+peer.getSessionID());	
		}
		
	}

}
