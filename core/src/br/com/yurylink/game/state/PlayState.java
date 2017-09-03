package br.com.yurylink.game.state;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import br.com.yurylink.game.MyGdxGame;
import br.com.yurylink.game.configurations.util.Constantes;
import br.com.yurylink.handlers.BackgroundHandler;
import br.com.yurylink.handlers.ColisaoHandler;
import br.com.yurylink.handlers.ControlHandler;
import br.com.yurylink.handlers.ExplosaoHandler;
import br.com.yurylink.handlers.MeteorHandle;
import br.com.yurylink.handlers.TirosHandler;
import br.com.yurylink.sprites.Nave;

public class PlayState extends State{
	
    private Nave nave;

    private BitmapFont pontuacao;
    private ShapeRenderer shapeRenderer;
    private Texture upBtn;

    private BackgroundHandler bg;
    private TirosHandler tirosHandler;
    private MeteorHandle meteoroHandle;
    private ExplosaoHandler explosaoHandler;
    private ControlHandler controlHandler;
    private ShapeRenderer desenhadorShape;

    private float indicadorBg;

    protected PlayState(GameStateManager gsm) {
        super(gsm);

        desenhadorShape = new ShapeRenderer();

        nave = new Nave(100, 200);
        camera.setToOrtho(false,Constantes.GAME_WHIDTH/2, Constantes.GAME_HEIGHT/2 );

        pontuacao = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        upBtn = new Texture("direcionais\\up32.png");
        
        bg = new BackgroundHandler();
        explosaoHandler = new ExplosaoHandler();
        tirosHandler = new TirosHandler();
        meteoroHandle = new MeteorHandle();
        controlHandler = new ControlHandler();
    }

    @Override
    protected void handleRequest() {
        controlHandler.reconheceControleTeclado(nave, tirosHandler);
        controlHandler.reconheceControleTouchpad2(camera, nave, tirosHandler);
    }

    @Override
    public void update(float dt) {
        handleRequest();
        meteoroHandle.gerarMeteoro(camera);
        nave.update(dt,camera);
        tirosHandler.atualizaTiros(dt);
        meteoroHandle.atualizarMeteoros(dt);

        camera.position.y = nave.getPosition().y + Constantes.NAVE_OFFSET_CAMERA;
        camera.update();
        tirosHandler.removerTirosOffScreen(camera);
        meteoroHandle.removerObjOffScreen(camera);

        if(meteoroHandle.verificarColisao(nave.getGerenciadorColisao().getLista())){
            gsm.set(new MenuState(gsm));
        }
        explosaoHandler.add(meteoroHandle.handleColisionTiro(tirosHandler.getLista()));
        explosaoHandler.updateList(dt);
    }

    @Override
    public void render(SpriteBatch sprite) {
        sprite.begin();
        sprite.setProjectionMatrix(camera.combined);
        bg.renderBackGround(sprite, camera);
        tirosHandler.desenhaTiros(sprite);
        meteoroHandle.desenharMeteoro(sprite);
        explosaoHandler.desenhaExplosoes(sprite);
        sprite.draw(nave.getNave(), nave.getPosition().x, nave.getPosition().y);
        desenhaPontacao(sprite);


        controlHandler.desenharBotoes(sprite, camera);

        sprite.end();
        nave.getGerenciadorColisao().desenharLimites(desenhadorShape, camera, ColisaoHandler.RECTANGLE);
        meteoroHandle.desenharLimitesMeteoro(shapeRenderer, camera);
    }

    private void desenhaPontacao(SpriteBatch sb){
        pontuacao.draw(sb, "Pontuação: " + meteoroHandle.getPontuacao(), camera.position.x - (camera.viewportWidth/2), camera.position.y + (camera.viewportHeight/2));
    }

    @Override
    public void dispose() {
        bg.dispose();
        nave.dispose();
        meteoroHandle.disposeAll();
        tirosHandler.disposeAll();
        pontuacao.dispose();
    }

	
}
