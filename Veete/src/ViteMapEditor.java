import br.com.etyllica.EtyllicaFrame;
import br.com.vite.map.MapApplication;


public class ViteMapEditor extends EtyllicaFrame{

	public ViteMapEditor() {
		super(800, 600);
	}
	
	public static void main(String[] args){
		ViteMapEditor map = new ViteMapEditor();
		map.updateDelay = 40;
		map.drawDelay = 40;
		map.init();
	}

	@Override
	public void startGame() {
		setMainApplication(new MapApplication(w, h));
	}
	
}
