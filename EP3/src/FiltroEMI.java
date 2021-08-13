public class FiltroEMI implements Filtro {
	int argFiltro;
	public void FiltroEMI (int argFiltro) {
		this.argFiltro = argFiltro;
	}
	public boolean filtra (Produto p) {
		if(p.getQtdEstoque() <= (Integer) argFiltro) return true;
		return false;	
	}

}