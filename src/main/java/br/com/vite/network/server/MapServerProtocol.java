package br.com.vite.network.server;

import br.com.midnight.model.Peer;
import br.com.midnight.protocol.common.StringServerProtocol;

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
			
		sendTCPtoAll("Hallu "+peer.getSessionID());
	}

}
