import java.io.PrintWriter;
public class Negrito extends Fonte {
	Fonte fonte;
	public Negrito (Fonte fonte) {
		this.fonte = fonte;
	}
	public void imprime (Produto p, PrintWriter out) {
		out.print("<span style=\"font-weight:bold\">");
		fonte.imprime(p, out);
		out.print("</span>");

	}	
}