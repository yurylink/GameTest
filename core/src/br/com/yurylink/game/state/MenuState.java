package br.com.yurylink.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.com.yurylink.game.configurations.util.Constantes;

public class MenuState extends State{
	
	 private Texture background;
	    private Texture playButton;
	    private BitmapFont tituloGame;

	    public MenuState(GameStateManager gsm) {
	        super(gsm);
	        background = new Texture("bg.png");
	        playButton = new Texture("Play-Button-PNG-Transparent-Image.png");
	        tituloGame = new BitmapFont();
	        camera.setToOrtho(false,Constantes.GAME_WHIDTH/2, Constantes.GAME_HEIGHT/2 );
	    }

	    @Override
	    public void handleRequest() {
	        if(Gdx.input.justTouched()){
	            gsm.set(new PlayState(gsm));
	            dispose();
	        }
	        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
	            gsm.set(new PlayState(gsm));
	        }
	    }

	    @Override
	    public void update(float dt) {
	        handleRequest();
	    }

	    @Override
	    public void render(SpriteBatch sprite) {
	        sprite.setProjectionMatrix(camera.combined);
	        sprite.begin();
	        sprite.draw(background, 0, 0, Constantes.GAME_WHIDTH, Constantes.GAME_HEIGHT);
	        sprite.draw(playButton, camera.position.x - (playButton.getWidth()/2) , camera.position.y - (playButton.getHeight()/2) );
	        tituloGame.draw(sprite,"Meteor Assault", 70, 300);
	        sprite.end();
	    }

	    @Override
	    public void dispose() {
	        background.dispose();
	        playButton.dispose();
	    }
	
}
