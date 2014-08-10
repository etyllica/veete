package br.com.vite.tile;


public abstract class TileLayer implements DrawableOffset {

	private long uniqueId;
	
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
