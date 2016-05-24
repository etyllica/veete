package br.com.vite.network.server;

import br.com.midnight.model.Peer;
import br.com.midnight.server.TCPServer;
import examples.simpletcp.client.SimpleClientProtocol;

public class MapServer extends TCPServer {

	private MapServerProtocol listener;

	public MapServer(int port) {
		super(port);

		handshaker = new MapHandshaker();
		
		listener = new MapServerProtocol(SimpleClientProtocol.DEFAULT_PREFIX);

		addProtocol(SimpleClientProtocol.DEFAULT_PREFIX, listener);
	}
	
	@Override
	public void start() {
		super.start();
	}
	
	@Override
	public void joinPeer(Peer peer) {
		System.out.println("HandShakePeer "+peer.getSessionID()+" connected.");
		listener.addPeer(peer);			
	}
	
}
