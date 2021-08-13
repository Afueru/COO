import java.awt.*;
public class FxBall extends Ball implements IBall {
	double [] rastrox;
	double [] rastroy;
	int livre;
	public FxBall(double cx, double cy, double width, double height, Color color, double speed, double vx, double vy) {
		super(cx, cy, width, height, color, speed, vx, vy);
		rastrox =  new double [100];
		rastroy = new double [100];
		livre = 0;
		for (int i = 0; i < rastrox.length; i++) rastrox[i] = cx;
		for (int j = 0; j < rastroy.length; j++) rastroy[j] = cx;

	}

	public void draw () {
		Color color =  getColor();
		GameLib.setColor(color);
		GameLib.fillRect(getCx(), getCy(), getWidth(), getHeight());
		rastrox[rastrox.length - 1] = getCx();
		rastroy[rastroy.length - 1] = getCy();
		double deformacaox = 100 * 0.2;
		double deformacaoy = 100 * 0.2;
		int transicao = color.getAlpha()/rastrox.length;
		if (color.getAlpha() > 25) color = new Color (color.getRed(),color.getGreen(),color.getBlue(),25);

		for (int i = rastrox.length - 1; i >= 0; i--) {
			GameLib.setColor(color);
			GameLib.fillRect(rastrox[i], rastroy[i],deformacaox,deformacaoy);
			if (color.getAlpha() - transicao <= 0 ) color = new Color (color.getRed(),color.getGreen(),color.getBlue(),0);
			else if (i == 25) color = new Color(color.getRed(),color.getGreen(),color.getBlue(), color.getAlpha() - 1);           //Color (float r, float g, float b, float a)
			deformacaoy = deformacaoy - 0.2;
			deformacaox = deformacaox - 0.2;


		}
		for (int j = 0; j < rastrox.length - 1; j++) {
			rastrox[j] = rastrox [j + 1];
			rastroy[j] = rastroy [j + 1];

		}

		









		/*double e = getCx() + getWidth()/2;
		if (getVx() != getVy()) {
			for (double f = getCy() + getHeight()/2; f >= getCy() - getWidth()/2; f = f - 0.1) {
				GameLib.drawLine(e,f,getCx() - 10*getWidth()*getVx(),getCy() - 10*getHeight()*getVy());
			}

		}
		else {
			for (double d = getCy() - getHeight()/2;d <= getCy() + getHeight()/2; d = d + 0.1) {
				GameLib.drawLine(e,d,getCx() - 10*getWidth()*getVx(),getCy() - 10*getHeight()*getVy());
				e = e - 0.1;
			}
		}*/
	}
}