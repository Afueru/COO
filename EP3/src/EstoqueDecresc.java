public class EstoqueDecresc implements Criterio {
	public void seleciona (int j, Produto [] produtos, Produto x, int ini) {
		while(j >= ini){
			if(x.getQtdEstoque() > produtos[j].getQtdEstoque()){
	
				produtos[j + 1] = produtos[j];
				j--;
			}
			else break;
		}
		
		produtos[j + 1] = x;
		return;
	}
	public int seleciona (int i, int j, Produto [] produtos, Produto x) {
		while (true) {

			do{ 
				j--;

			} while(produtos[j].getQtdEstoque() < x.getQtdEstoque());
			
			do{
				i++;

			} while(produtos[i].getQtdEstoque() > x.getQtdEstoque());
			if(i < j){
				Produto temp = produtos[i];
				produtos[i] = produtos[j]; 				
				produtos[j] = temp;
			}
			else return j;		

		}
	}
	
}