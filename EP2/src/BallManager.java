import java.awt.Color;
import java.lang.reflect.*;

/**
	Classe que gerencia uma ou mais bolas presentes em uma partida. Esta classe é a responsável por instanciar 
	e gerenciar a bola principal do jogo (aquela que existe desde o ínicio de uma partida), assim como eventuais 
	bolas extras que apareçam no decorrer da partida. Esta classe também deve gerenciar a interação da(s) bola(s)
	com os alvos, bem como a aplicação dos efeitos produzidos para cada tipo de alvo atingido.
*/

public class BallManager {

	/**
		Atributo privado que representa a bola principal do jogo.	
	*/

	private IBall theBall = null;

	/**
		Atributo privado que representa o tipo (classe) das instâncias de bola que serão criadas por esta classe.
	*/

	private Class<?> ballClass = null;

	/**
		Construtor da classe BallManager.
		
		@param className nome da classe que define o tipo das instâncias de bola que serão criadas por esta classe. 
	*/
	private long [] bTimer = new long [21];

	private long [] dTimer = new long [20];

	private double defaultSpeed = 0.65;

	private IBall [] balls = new IBall [20];

	private int livre = 0;



	public BallManager(String className){
		for (int i = 0; i < bTimer.length; i++) bTimer[i] = -1;
		for (int j = 0; j < dTimer.length; j++) dTimer[j] = -1;

		try{
			ballClass = Class.forName(className);
		}
		catch(Exception e){

			System.out.println("Classe '" + className + "' não reconhecida... Usando 'Ball' como classe padrão.");
			ballClass = Ball.class;
		}
	}

	/**
		Recebe as componetes x e y de um vetor, e devolve as componentes x e y do vetor normalizado (isto é, com comprimento igual a 1.0).
	
		@param x componente x de um vetor que representa uma direção.
		@param y componente y de um vetor que represetna uma direção.

		@return array contendo dois valores double que representam as componentes x (índice 0) e y (índice 1) do vetor normalizado (unitário).
	*/
	private double [] normalize(double x, double y){

		double length = Math.sqrt(x * x + y * y);

		return new double [] { x / length, y / length };
	}
	
	/**
		Cria uma instancia de bola, a partir do tipo (classe) cujo nome foi passado ao construtor desta classe.
		O vetor direção definido por (vx, vy) não precisa estar normalizado. A implemntação do método se encarrega
		de fazer a normalização.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
		@param vx componente x do vetor (não precisa ser unitário) que representa a direção da bola.
		@param vy componente y do vetor (não precisa ser unitário) que representa a direção da bola.
	*/

	private IBall createBallInstance(double cx, double cy, double width, double height, Color color, double speed, double vx, double vy){
		IBall ball = null;
		double [] v = normalize(vx, vy);

		try{
			Constructor<?> constructor = ballClass.getConstructors()[0];
			ball = (IBall) constructor.newInstance(cx, cy, width, height, color, speed, v[0], v[1]);
		}
		catch(Exception e){

			System.out.println("Falha na instanciação da bola do tipo '" + ballClass.getName() + "' ... Instanciando bola do tipo 'Ball'");
			ball = new Ball(cx, cy, width, height, color, speed, v[0], v[1]);
		}

		return ball;
	} 

	/**
		Cria a bola principal do jogo. Este método é chamado pela classe Pong, que contem uma instância de BallManager.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
		@param vx componente x do vetor (não precisa ser unitário) que representa a direção da bola.
		@param vy componente y do vetor (não precisa ser unitário) que representa a direção da bola.
	*/

	public void initMainBall(double cx, double cy, double width, double height, Color color, double speed, double vx, double vy){

		theBall = createBallInstance(cx, cy, width, height, color, speed, vx, vy);
	}

	/**
		Método que desenha todas as bolas gerenciadas pela instância de BallManager.
		Chamado sempre que a(s) bola(s) precisa ser (re)desenhada(s).
	*/

	public void draw(){
		for (int i = 0; i < livre; i++)balls[i].draw();
		theBall.draw();
	}
	
	/**
		Método que atualiza todas as bolas gerenciadas pela instância de BallManager, em decorrência da passagem do tempo.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void update(long delta){
		long agora = System.currentTimeMillis();
		if (bTimer[0] != -1 && agora >= bTimer[0]) {
			theBall.setSpeed(defaultSpeed);
			bTimer[0] = -1;
		}
		for (int j = 0; j < livre; j++) {
			if (bTimer[j+1] != -1 && agora >= bTimer[j+1]) {
				balls[j].setSpeed(defaultSpeed);
				bTimer[j+1] = -1;
			}
			if (dTimer[j] != -1 && agora >= dTimer[j] - 800) {
				Color aux = balls[j].getColor();
				Color dis;
				if (aux.getAlpha() - 1 > 0) dis = new Color (aux.getRed(),aux.getGreen(),aux.getBlue(), aux.getAlpha() - 1);
				else dis = new Color (aux.getRed(),aux.getGreen(),aux.getBlue(), 0);
				balls[j].setColor(dis);


			}
			if (dTimer[j] != -1 && agora >= dTimer[j]) {
				for (int a = j; a < dTimer.length - 1; a++) dTimer[a] = dTimer [a+1];
				for (int b = j + 1; b < bTimer.length - 1; b++) bTimer[b] = bTimer[b + 1];
				for (int c = j; c < livre - 1; c++) balls[c] = balls[c + 1];
				livre--;

			}

		}

		for (int i = 0; i < livre; i++)balls[i].update(delta);

		theBall.update(delta);
	}
	
	/**
		Método que processa as colisões entre as bolas gerenciadas pela instância de BallManager com uma parede.

		@param wall referência para uma instância de Wall para a qual será verificada a ocorrência de colisões.
		@return um valor int que indica quantas bolas colidiram com a parede (uma vez que é possível que mais de 
		uma bola tenha entrado em contato com a parede ao mesmo tempo).
	*/

	public int checkCollision(Wall wall){

		int hits = 0;
		for (int i = 0; i < livre; i++) if(balls[i].checkCollision(wall)) hits++;
		if(theBall.checkCollision(wall)) hits++;

		return hits;
	}

	/**
		Método que processa as colisões entre as bolas gerenciadas pela instância de BallManager com um player.

		@param player referência para uma instância de Player para a qual será verificada a ocorrência de colisões.
	*/
	
	public void checkCollision(Player player){
		for (int i = 0; i < livre; i++)balls[i].checkCollision(player);
		theBall.checkCollision(player);
	}

	/**
		Método que processa as colisões entre as bolas gerenciadas pela instância de BallManager com um alvo.

		@param target referência para uma instância de Target para a qual será verificada a ocorrência de colisões.
	*/

	public void checkCollision(Target target){
		if (theBall.checkCollision(target)) {
			//GameLib.drawExplosion(target.getCx(),target.getCy(), theBall.getWidth());
			if (target instanceof BoostTarget && bTimer[0] == -1) {				
				BoostTarget bt = (BoostTarget) target;
				defaultSpeed = theBall.getSpeed();
				bTimer[0] = System.currentTimeMillis() + bt.BOOST_DURATION;
				theBall.setSpeed(theBall.getSpeed()*bt.BOOST_FACTOR*1.75);
			}
			if (target instanceof DuplicatorTarget) {
				DuplicatorTarget dt = (DuplicatorTarget) target;
				balls[livre]  = createBallInstance(theBall.getCx(), theBall.getCy() - 7, theBall.getWidth(), theBall.getHeight(), Color.RED, defaultSpeed, theBall.getVx(), theBall.getVy());
				dTimer[livre] = System.currentTimeMillis() + dt.EXTRA_BALL_DURATION;
				livre++;
				if (livre == balls.length) livre = 0;
				//System.out.println(livre);
			}
		}
		for (int i = 0; i < livre; i++) {
			if (balls[i].checkCollision(target)) {
				if (target instanceof BoostTarget && bTimer[i+1] == -1) {
					BoostTarget b = (BoostTarget) target;
					bTimer[i+1] = System.currentTimeMillis() + b.BOOST_DURATION;
					balls[i].setSpeed(balls[i].getSpeed()*b.BOOST_FACTOR*1.75);
				}
				if (target instanceof DuplicatorTarget) {
					DuplicatorTarget d = (DuplicatorTarget) target;
					balls[livre] = createBallInstance(balls[i].getCx(), balls[i].getCy() - 7, balls[i].getWidth(), balls[i].getHeight(), Color.RED, defaultSpeed, balls[i].getVx(), balls[i].getVy());
					dTimer[livre] = System.currentTimeMillis() + d.EXTRA_BALL_DURATION;
					livre++;
					if (livre == balls.length) livre = 0;
					//System.out.println(livre);
				}
			}
		}
	}
}


