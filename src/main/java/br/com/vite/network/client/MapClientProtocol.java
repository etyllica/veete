package br.com.vite.network.client;

import br.com.midnight.model.Peer;
import br.com.midnight.protocol.common.StringClientProtocol;

public class MapClientProtocol extends StringClientProtocol {

	public static final String PREFIX_VEETE = "vt";
	
	public static final String PREFIX_ADD_TILE = "a";
	public static final String PREFIX_REMOVE_TILE = "r";
	
	public MapClientProtocol() {
		super(PREFIX_VEETE);
	}

	public void sendHandShake() {
		sendTCP("hi");
	}
		
	@Override
	public void receiveTCP(Peer peer, String msg) {
		System.out.println(this.getClass().getSimpleName()+"(TCP) received "+msg);
	}

	@Override
	public void receiveUDP(Peer peer, String msg) {
		System.out.println(this.getClass().getSimpleName()+"(UDP) received "+msg);
	}

	public void sendWriteTile(int x, int y, String id) {
		sendTCP(PREFIX_ADD_TILE+" "+id+" "+x+" "+y);
	}
	
}
