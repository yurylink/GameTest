package br.com.yurylink.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import br.com.yurylink.game.MyGdxGame;
import br.com.yurylink.game.configurations.util.Constantes;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Constantes.GAME_HEIGHT;
		config.width = Constantes.GAME_WHIDTH;
		config.title = Constantes.GAME_TITULO;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
