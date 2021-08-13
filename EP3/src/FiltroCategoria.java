public class FiltroCategoria implements Filtro {
	String argFiltro;
	public FiltroCategoria (String argFiltro) {
		this.argFiltro = argFiltro;
	}
	public boolean filtra (Produto p) {
		if(p.getCategoria().equalsIgnoreCase((String)argFiltro)) return true;
		return false;
	}	
}