import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.context.Application;
import br.com.vite.IsometricMap;
import br.com.vite.OrthogonalMap;


public class ViteMapEditor extends EtyllicaFrame {

	public ViteMapEditor() {
		super(800, 600);
	}
	
	public static void main(String[] args){
		ViteMapEditor map = new ViteMapEditor();
		map.init();
	}

	public Application startApplication() {
		return new IsometricMap(w, h);
		//return new OrthogonalMap(w, h);
	}
	
}
