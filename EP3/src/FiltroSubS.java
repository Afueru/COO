public class FiltroSubS implements Filtro {
	String argFiltro;
	public FiltroSubS (String subString) {
		this.argFiltro = subString;
	}
	
	public boolean filtra (Produto p) {
		if ( p.getDescricao().toLowerCase().indexOf(argFiltro.toLowerCase()) != -1 ) return true;
		return false;	
	}

}