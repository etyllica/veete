import examples.CollaborativeMapApplication;
import examples.HexagonalMapApplication;
import examples.IsometricMapApplication;
import examples.OrthogonalMapApplication;
import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;


public class ViteMapEditor extends Etyllica {

	public ViteMapEditor() {
		super(800, 600);
	}
	
	public static void main(String[] args){
		ViteMapEditor map = new ViteMapEditor();
		map.setTitle("Veete");
		map.init();
	}

	public Application startApplication() {
		
		initialSetup("../");
			
		//return new IsometricMapApplication(w, h);
		//return new OrthogonalMapApplication(w, h);
		//return new HexagonalMapApplication(w, h);
		return new CollaborativeMapApplication(w, h);
	}
	
}
