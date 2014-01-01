import br.com.luvia.Luvia;
import br.com.vite.MapApplicationGL;


public class ViteMapEditorGL extends Luvia{

	public ViteMapEditorGL() {
		super(800, 600);
	}
	
	public static void main(String[] args){
		ViteMapEditorGL map = new ViteMapEditorGL();
		/*map.updateDelay = 40;
		map.drawDelay = 40;*/
		map.init();
	}

	@Override
	public void startGame() {
		setMainApplication(new MapApplicationGL(w, h));
	}
	
}
