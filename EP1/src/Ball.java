import java.awt.*;
import java.util.Random;

/**
	Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
	instancia um objeto deste tipo quando a execução é iniciada.
*/

public class Ball {
	double cx;
	double cy;
	double width;
	double height;
	Color color;
	double speed;
	int lado;
	int bally;
	int ballx;
	boolean saiu;
	boolean bateuy;

	/**
		Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
		(em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
		aleatóriamente pelo construtor.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
	*/

	public Ball(double cx, double cy, double width, double height, Color color, double speed){
		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.color = color;
		this.speed = speed;
		int lado = escolheLado();
		this.lado = lado;
		this.ballx = 0;
		this.bally = 0;
		this.saiu = false;
		this.bateuy = false;
	
	}
    public int escolheLado () {
    	Random random = new Random ();
    	int lado = random.nextInt();
    	if (lado == 0) return escolheLado();
    	return lado;
    }




	/**
		Método chamado sempre que a bola precisa ser (re)desenhada.
	*/

	public void draw(){

		GameLib.setColor(this.color);
		GameLib.fillRect(this.cx, this.cy, 20, 20);
	}

	/**
		Método chamado quando o estado (posição) da bola precisa ser atualizado.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void update(long delta){
		long inicio = System.currentTimeMillis();
		if (saiu == false) {
			if (lado > 0) ballx = 1;
			else ballx = -1;
			bally = 1;
			saiu = true;
	}
        cx = cx + 1.75*speed*ballx;
        cy = cy + 1.75*speed*bally;
	    delta = System.currentTimeMillis() - inicio;
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com um jogador.
	
		@param playerId uma string cujo conteúdo identifica um dos jogadores.
	*/

	public void onPlayerCollision(String playerId){
		if (bateuy == true) bally = -bally;
		else ballx = -ballx;
		bateuy = false;

	}

	/**
		Método chamado quando detecta-se uma colisão da bola com uma parede.

		@param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	*/

	public void onWallCollision(String wallId){
		if (wallId == "Top" || wallId == "Bottom") bally = -bally;
		if (wallId == "Right" || wallId == "Left") ballx = -ballx;

	}

	/**
		Método que verifica se houve colisão da bola com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
	
	public boolean checkCollision(Wall wall) {		
		if (wall.getId() == "Top" && cy - height/2 <= wall.getCy() + wall.getHeight()/2) return true;
		if (wall.getId() == "Bottom" && cy + height/2 >= wall.getCy() - wall.getHeight()/2) return true;
		if (wall.getId() == "Right" && cx + width/2 >= wall.getCx() - wall.getWidth()/2) return true;
		if (wall.getId() == "Left" && cx - width/2 <= wall.getCx() + wall.getWidth()/2) return true;
		return false;
	}

	/**
		Método que verifica se houve colisão da bola com um jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	

	public boolean checkCollision(Player player){
		if (checaColisaoX(player)){
			if (checaColisaoY(player)) bateuy = true;
			return true;
			}
        return false;
	}
	public boolean checaColisaoY (Player player) {
		if (cy + height/2 >= player.getCy() - player.getHeight()/2 && cy - height/2 <= player.getCy() + player.getHeight()/2) { //Colisao vertical
			if (cx - width/2 < player.getCx() + player.getWidth()/2 && cx + width/2 > player.getCx() - player.getWidth()/2) return true; //Checa colisao vertical
		}
		return false;
	}
	public boolean checaColisaoX (Player player) {
		if (cx - width/2 <= player.getCx() + player.getWidth()/2 + 1 && cx + width/2 >= player.getCx() - player.getWidth()/2 - 1) { //Colisao horizontal
			if (cy + height/2 > player.getCy() - player.getHeight()/2 && cy - height/2 < player.getCy() + player.getHeight()/2) return true;
		}
	return false;
	}

	/**
		Método que devolve a coordenada x do centro do retângulo que representa a bola.
		@return o valor double da coordenada x.
	*/
	
	public double getCx(){

		return cx;
	}

	/**
		Método que devolve a coordenada y do centro do retângulo que representa a bola.
		@return o valor double da coordenada y.
	*/

	public double getCy(){

		return cy;
	}

	/**
		Método que devolve a velocidade da bola.
		@return o valor double da velocidade.

	*/

	public double getSpeed(){

		return speed;
	}

}
