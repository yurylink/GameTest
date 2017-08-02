package br.com.yurylink.game.configurations.util;

public class Constantes {

	public static final String GAME_TITULO = "Aircraft Math";

	public static final int GAME_WHIDTH = 480;
	public static final int GAME_HEIGHT = 800;

	public static final int NAVE_OFFSET_CAMERA = 150;
	public static final int NAVE_VELOCIDADE = 2;

	public static final int VELOCIDADE_TIRO_INICIAL = NAVE_VELOCIDADE * 60;
	public static final Long TIROS_TIME_GAP = 250l;

	public static final int METEORO_VELOCIDADE = 1;
	public static final Long METEOROS_TIME_GAP = 2000l;

	public static void printarRelatorio(String mensagem) {
		System.out.println(mensagem);
	}
}
