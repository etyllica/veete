import br.com.etyllica.EtyllicaFrame;
import br.com.vite.map.MapApplication;


public class Veete extends EtyllicaFrame{

	public Veete() {
		super(800, 600);
	}
	
	public static void main(String[] args){
		Veete map = new Veete();
		map.updateDelay = 40;
		map.drawDelay = 40;
		map.init();
	}

	@Override
	public void startGame() {
		setMainApplication(new MapApplication(w, h));
	}
	
}
