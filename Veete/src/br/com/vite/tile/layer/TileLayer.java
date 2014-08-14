package br.com.vite.tile.layer;


public abstract class TileLayer {

	protected long uniqueId;
	
	public TileLayer() {
		super();
	}
	
	public TileLayer(long uniqueId) {
		super();
		
		this.uniqueId = uniqueId;
	}

	public long getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(long uniqueId) {
		this.uniqueId = uniqueId;
	}
		
}
