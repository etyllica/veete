package br.com.vite.map;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.PointerState;
import br.com.etyllica.core.video.Graphic;
import br.com.vite.tile.IsometricTile;

public class MapApplication extends Application {

	private IsometricTile[][] tiles;

	private final int tileSize = 64;

	private IsometricTile lastTile;

	public MapApplication(float w, float h) {
		super(w, h);
		// TODO Auto-generated constructor stub
	}

	final int columns = 32;
	final int lines = 13;

	@Override
	public void load() {

		tiles = new IsometricTile[lines][columns];

		int offsetX = 0;

		for(int j=0;j<lines;j++){

			offsetX = (tileSize/2)*(j%2);

			for(int i=0;i<columns;i++){

				tiles[j][i] = new IsometricTile(offsetX+i*tileSize, 50+(tileSize/4)*j, tileSize);	
			}
		}

		lastTile = tiles[0][0];

		loading = 100;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {

		if(event.getState()==PointerState.MOVE){

			for(int j=0;j<lines;j++){

				for(int i=0;i<columns;i++){

					IsometricTile tile = tiles[j][i];

					if(tile.colideIsometric(event.getX(), event.getY())){

						if(lastTile!=tile){

							tile.setColor(Color.GREEN);
							lastTile.setColor(Color.BLACK);
							lastTile = tile;

						}

						return null;
					}

				}
			}

		}

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Graphic g) {

		for(int j=0;j<lines;j++){

			for(int i=0;i<columns;i++){

				IsometricTile tile = tiles[j][i];
				tile.draw(g);
			}
		}

	}

}
