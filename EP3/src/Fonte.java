import java.io.PrintWriter;
public class Fonte implements Texto{
	public void imprime (Produto p, PrintWriter out) {
		out.print(p.formataParaImpressao());
	}
}