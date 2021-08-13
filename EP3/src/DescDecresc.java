public class DescDecresc implements Criterio {
	public void seleciona (int j, Produto [] produtos, Produto x, int ini) {
		while(j >= ini){
			if( x.getDescricao().compareToIgnoreCase(produtos[j].getDescricao()) > 0 ){
	
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

			} while(produtos[j].getDescricao().compareToIgnoreCase(x.getDescricao()) < 0);
			
			do{
				i++;

			} while(produtos[i].getDescricao().compareToIgnoreCase(x.getDescricao()) > 0);
			if(i < j){
				Produto temp = produtos[i];
				produtos[i] = produtos[j]; 				
				produtos[j] = temp;
			}
			else return j;		

		}
	}
}