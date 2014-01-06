package br.com.vite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.core.video.Graphic;
import br.com.vite.collection.grassland.floor.Grass;
import br.com.vite.collection.grassland.floor.Marble;
import br.com.vite.collection.tree.PalmTree1;
import br.com.vite.tile.ImageTileLayer;
import br.com.vite.tile.IsometricTile;

public class MapApplication extends Application {

	int uniqueId = 0;

	//TileLayers
	
	private ImageTileLayer selectedTile;
	
	private ImageTileLayer selectedObject;
	
	private Grass grass;
	private Marble marble;
	private PalmTree1 tree;

	private IsometricTile[][] tiles;

	private final int tileSize = 64;

	private IsometricTile lastTile;

	private BufferedImage tileBorder;
	private BufferedImage tileFill;
	
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private boolean middlePressed = false;
	
	private int[] target = new int[2];

	public MapApplication(int w, int h) {
		super(w, h);
	}

	final int columns = 13;
	final int lines = 32;

	int offsetY = 50;
	int offsetX = 0;

	@Override
	public void load() {

		//create buffer
		int x = 0;
		int y = 0;

		int w = tileSize;
		int h = tileSize/2;

		createTileBorder(x,y,w,h);

		createImageTiles();
		
		target[0] = 0;
		target[1] = 0;
		
		tiles = new IsometricTile[lines][columns];
		
		loading = 10;

		int oddOffsetX = 0;

		for(int j=0;j<lines;j++){

			oddOffsetX = (tileSize/2)*(j%2);

			for(int i=0;i<columns;i++){

				tiles[j][i] = new IsometricTile(oddOffsetX+i*tileSize, offsetY+(tileSize/4)*j, tileSize);

				tiles[j][i].setLayer(selectedTile);
				
			}
		}
		
		loading = 20;

		lastTile = tiles[target[0]][target[1]];

		loading = 30;

		updateAtFixedRate(80);
		
		loading = 100;
	}

	private void createTileBorder(int x, int y, int w, int h){

		tileBorder = new BufferedImage(w, h+1, BufferedImage.TYPE_INT_ARGB);
		tileFill = new BufferedImage(w, h+1, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = tileBorder.createGraphics();
		g.setColor(Color.BLACK);

		Polygon polygon = new Polygon();

		polygon.addPoint(x, y+h/2);
		polygon.addPoint(x+w/2, y);
		polygon.addPoint(x+w, y+h/2);
		polygon.addPoint(x+w/2, y+h);

		g.drawPolygon(polygon);

		//Create Fill
		g = tileFill.createGraphics();
		g.setColor(Color.GREEN);
		g.fillPolygon(polygon);
	}

	private void createImageTiles(){
		grass = new Grass(genereateUniqueId(), 0);
		marble = new Marble(genereateUniqueId(), 0);
		tree = new PalmTree1(genereateUniqueId());
		
		selectedTile = grass;
		
		selectedObject = tree;
		
	}

	private int genereateUniqueId(){
		return uniqueId++;
	}
	
	int mx = 0;
	int my = 0;
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
				
		my = event.getY();
		mx = event.getX();
		
		if(event.onButtonDown(MouseButton.MOUSE_BUTTON_LEFT)){
			leftPressed = true;
		}else if(event.onButtonUp(MouseButton.MOUSE_BUTTON_LEFT)){
			leftPressed = false;
		}
		
		if(event.onButtonDown(MouseButton.MOUSE_BUTTON_RIGHT)){
			rightPressed = true;
		}else if(event.onButtonUp(MouseButton.MOUSE_BUTTON_RIGHT)){
			rightPressed = false;
		}
		
		if(event.onButtonDown(MouseButton.MOUSE_BUTTON_MIDDLE)){
			middlePressed = true;
		}else if(event.onButtonUp(MouseButton.MOUSE_BUTTON_MIDDLE)){
			middlePressed = false;
		}
				
		return GUIEvent.NONE;
	}
	
	@Override
	public void timeUpdate(long now){


		getClicked(mx, my);
		
		lastTile = tiles[target[0]][target[1]];
		
		if(leftPressed){
			lastTile.setLayer(selectedTile);
		}else if(rightPressed){
			lastTile.setObjectLayer(selectedObject);
		}else if(middlePressed){			
			lastTile.setLayer(null);
		}
		
	}

	private int[] getClicked(int mouseX, int mouseY){
		
		int column = (int)(mouseX-offsetX)/tileSize;

		int line = (int)(mouseY-offsetY)/(tileSize/4);

		if(line<=0){
			line = 1;
		}else if (line>=lines){
			line = lines-1;
		}

		if(column<=0){
			column = 1;
		}else if (column>=columns){
			column = columns-1;
		}

		for(int j=line-1;j<line+1;j++){

			for(int i=column-1;i<column+1;i++){

				if(tiles[j][i].colideIsometric(mouseX, mouseY)){
					
					target[0] = j;
					target[1] = i;
				}

			}
		}
		
		return target;

	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.TSK_1)){
			selectedTile = grass;
		}
		
		if(event.isKeyDown(KeyEvent.TSK_2)){
			selectedTile = marble;
		}
		
		return GUIEvent.NONE;
	}

	@Override
	public void draw(Graphic g) {

		for(int j=0;j<lines;j++){

			for(int i=0;i<columns;i++){

				IsometricTile tile = tiles[j][i];

				tile.drawTile(g);
								
				//Draw Grid
				g.drawImage(tileBorder, tile.getX(),tile.getY());
				
				tile.drawObject(g);
				
			}
		}

		g.setAlpha(50);
		g.drawImage(tileFill, lastTile.getX(), lastTile.getY());
		g.setAlpha(100);
		
	}

}
