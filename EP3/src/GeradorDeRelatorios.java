import java.io.PrintWriter;
import java.io.IOException;

public class GeradorDeRelatorios {


/*****************************************************************/
/*****************************************************************/
/***EEEEEEEEEEEEEEEEEEEEEEPPPPPPPPPPPPPPPPP    333333333333333****/   
/***E::::::::::::::::::::EP::::::::::::::::P  3:::::::::::::::33**/ 
/***E::::::::::::::::::::EP::::::PPPPPP:::::P 3::::::33333::::::3*/
/***EE::::::EEEEEEEEE::::EPP:::::P     P:::::P3333333     3:::::3*/
/*****E:::::E       EEEEEE  P::::P     P:::::P            3:::::3*/
/*****E:::::E               P::::P     P:::::P            3:::::3*/
/*****E::::::EEEEEEEEEE     P::::PPPPPP:::::P     33333333:::::3**/ 
/*****E:::::::::::::::E     P:::::::::::::PP      3:::::::::::3***/  
/*****E:::::::::::::::E     P::::PPPPPPPPP        33333333:::::3**/ 
/*****E::::::EEEEEEEEEE     P::::P                        3:::::3*/
/*****E:::::E               P::::P                        3:::::3*/
/*****E:::::E       EEEEEE  P::::P                        3:::::3*/
/***EE::::::EEEEEEEE:::::EPP::::::PP          3333333     3:::::3*/
/***E::::::::::::::::::::EP::::::::P          3::::::33333::::::3*/
/***E::::::::::::::::::::EP::::::::P          3:::::::::::::::33**/ 
/**	EEEEEEEEEEEEEEEEEEEEEEPPPPPPPPPP           333333333333333****/
/*****************************************************************/
/*****************************************************************/
/*****************************************************************/
/****** Guilherme Kenzo Silva Oshiro - Numero USP: 11314988 ******/
/****** Guilherme Rodrigues Pisni    - Numero USP: 11270851 ******/
/****** Mark Poll Herrmann           - Numero USP: 11208291 ******/
/*****************************************************************/
/*****************************************************************/


	private Produto [] produtos;
	private SortStrategy algoritmo; 
	private Criterio criterio;
	private Texto texto;
	private Filtro filtro;

	public GeradorDeRelatorios(Produto [] produtos, SortStrategy algoritmo, Criterio criterio, Texto texto, Filtro filtro){

		this.produtos = new Produto[produtos.length];
		
		for(int i = 0; i < produtos.length; i++){
		
			this.produtos[i] = produtos[i];
		}

		this.algoritmo = algoritmo;
		this.criterio = criterio; 
		this.texto = texto;
		this.filtro = filtro;
	}

	private void ordena(int ini, int fim){
		try {
			algoritmo.sort(ini, fim, produtos, criterio);
		}
		catch (Exception e) {
			throw new RuntimeException("Algoritmo invalido!");
		}
	}
	
	public void geraRelatorio(String arquivoSaida) throws IOException {

		ordena(0, produtos.length - 1);

		PrintWriter out = new PrintWriter(arquivoSaida);

		out.println("<!DOCTYPE html><html>");
		out.println("<head><title>Relatorio de produtos</title></head>");
		out.println("<body>");
		out.println("Relatorio de Produtos:");
		out.println("<ul>");

		int count = 0;

		for(int i = 0; i < produtos.length; i++){

			Produto p = produtos[i];

			boolean selecionado = filtro.filtra(p);

			if(selecionado){


				out.print("<li>");

				/* Filtro subfiltro = new FiltroCategoria("Games");
				if (subfiltro.filtra(p)) {
					Texto textoespecial = new Cor("orange",new Italico(new Negrito(new Fonte())));          //exemplo de admninistração de texto por categoria "Games", deixando os itens de cor laranja
					textoespecial.imprime(p,out);
				}
				else */texto.imprime(p,out);

				out.println("</li>");
				count++;
			}
		}

		out.println("</ul>");
		out.println(count + " produtos listados, de um total de " + produtos.length + ".");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

	public static Produto [] carregaProdutos(){

		return new Produto [] { 

			new ProdutoPadrao( 1, "O Hobbit", "Livros", 2, 34.90),
			new ProdutoPadrao( 2, "Notebook Core i7", "Informatica", 5, 1999.90),
			new ProdutoPadrao( 3, "Resident Evil 4", "Games", 7, 79.90),
			new ProdutoPadrao( 4, "iPhone", "Telefonia", 8, 4999.90),
			new ProdutoPadrao( 5, "Calculo I", "Livros", 20, 55.00),
			new ProdutoPadrao( 6, "Power Glove", "Games", 3, 499.90),
			new ProdutoPadrao( 7, "Microsoft HoloLens", "Informatica", 1, 19900.00),
			new ProdutoPadrao( 8, "OpenGL Programming Guide", "Livros", 4, 89.90),
			new ProdutoPadrao( 9, "Vectrex", "Games", 1, 799.90),
			new ProdutoPadrao(10, "Carregador iPhone", "Telefonia", 15, 499.90),
			new ProdutoPadrao(11, "Introduction to Algorithms", "Livros", 7, 315.00),
			new ProdutoPadrao(12, "Daytona USA (Arcade)", "Games", 1, 12000.00),
			new ProdutoPadrao(13, "Neuromancer", "Livros", 5, 45.00),
			new ProdutoPadrao(14, "Nokia 3100", "Telefonia", 4, 249.99),
			new ProdutoPadrao(15, "Oculus Rift", "Games", 1, 3600.00),
			new ProdutoPadrao(16, "Trackball Logitech", "Informatica", 1, 250.00),
			new ProdutoPadrao(17, "After Burner II (Arcade)", "Games", 2, 8900.0),
			new ProdutoPadrao(18, "Assembly for Dummies", "Livros", 30, 129.90),
			new ProdutoPadrao(19, "iPhone (usado)", "Telefonia", 3, 3999.90),
			new ProdutoPadrao(20, "Game Programming Patterns", "Livros", 1, 299.90),
			new ProdutoPadrao(21, "Playstation 2", "Games", 10, 499.90),
			new ProdutoPadrao(22, "Carregador Nokia", "Telefonia", 14, 89.00),
			new ProdutoPadrao(23, "Placa Aceleradora Voodoo 2", "Informatica", 4, 189.00),
			new ProdutoPadrao(24, "Stunts", "Games", 3, 19.90),
			new ProdutoPadrao(25, "Carregador Generico", "Telefonia", 9, 30.00),
			new ProdutoPadrao(26, "Monitor VGA 14 polegadas", "Informatica", 2, 199.90),
			new ProdutoPadrao(27, "Nokia N-Gage", "Telefonia", 9, 699.00),
			new ProdutoPadrao(28, "Disquetes Maxell 5.25 polegadas (caixa com 10 unidades)", "Informatica", 23, 49.00),
			new ProdutoPadrao(29, "Alone in The Dark", "Games", 11, 59.00),
			new ProdutoPadrao(30, "The Art of Computer Programming Vol. 1", "Livros", 3, 240.00),
			new ProdutoPadrao(31, "The Art of Computer Programming Vol. 2", "Livros", 2, 200.00),
			new ProdutoPadrao(32, "The Art of Computer Programming Vol. 3", "Livros", 4, 270.00)
		};
	} 

	public static void main(String [] args) {
	
		Produto [] produtos = carregaProdutos();

		GeradorDeRelatorios gdr;

		SortStrategy algoritmo = new QuickSort();

		Criterio criterio = new PrecoCresc();

		Filtro filtro = new FiltroTodos();

		Texto texto = new Cor("cyan", new Negrito(new Italico(new Fonte())));

		gdr = new GeradorDeRelatorios(	produtos, algoritmo, criterio, 
						texto, 
						filtro);
		
		try{
			gdr.geraRelatorio("saida.html");
		}
		catch(IOException e){
			
			e.printStackTrace();
		}
	}
}
