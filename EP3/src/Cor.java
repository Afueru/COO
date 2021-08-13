import java.io.PrintWriter;
public class Cor extends Fonte {
	Fonte fonte;
	String cor;
	public Cor (String cor, Fonte fonte) {
		this.cor = cor.toLowerCase();
		this.fonte = fonte;
	}
	public void imprime (Produto p, PrintWriter out) {
				out.print("<span style=\"color:" + cor + "\">");
				fonte.imprime(p,out);
				out.print("</span>");
	}	
}