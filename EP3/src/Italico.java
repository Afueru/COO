import java.io.PrintWriter;
public class Italico extends Fonte {
	private Fonte fonte;
	public Italico (Fonte fonte) {
		this.fonte = fonte;
	}
	public void imprime(Produto p,PrintWriter out) {
		out.print("<span style=\"font-style:italic\">");
		fonte.imprime(p,out);
		out.print("</span>");
	}
}