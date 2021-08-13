public class FiltroPDI implements Filtro {
	double [] argFiltro = new double [2];
	public FiltroPDI (double a, double b) {
		this.argFiltro [0]= a;
		this.argFiltro [1]= b;
	}
	public boolean filtra (Produto p) {
		if(p.getPreco() >= argFiltro[0] && p.getPreco() <= argFiltro[1]) return true;
		return false;	
	}

}