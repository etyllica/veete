package br.com.vite.tile;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.Layer;

public class IsometricTile extends Layer{

	private BufferedImage buffer;
	private Polygon polygon;
	
	private Color color = Color.BLACK;
	
	public IsometricTile(int x, int y, int w){
		super(x,y,w,w/2);
		
		int h = w/2;
		
		polygon = new Polygon();
		
		polygon.addPoint(x, y+h/2);
		polygon.addPoint(x+w/2, y);
		polygon.addPoint(x+w, y+h/2);
		polygon.addPoint(x+w/2, y+h);
		
		/*buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		
		buffer.createGraphics();
		
		Polygon polygon = new Polygon();
		
		polygon.addPoint(x, y+h/2);
		polygon.addPoint(x+w/2, y);
		polygon.addPoint(x+w, y+h/2);
		polygon.addPoint(x+w/2, y+h);
		
		buffer.getGraphics().setColor(Color.BLACK);
		buffer.getGraphics().drawPolygon(polygon);*/
		
	}
	
	@Override
	public void draw(Graphic g){
		
		g.setColor(color);
		
		g.draw(polygon);		
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}
	
}
