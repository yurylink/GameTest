package br.com.yurylink.sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import br.com.yurylink.animation.NaveAnimation;
import br.com.yurylink.colisao.NaveColisao;
import br.com.yurylink.game.configurations.util.Constantes;
import br.com.yurylink.handlers.ColisaoHandler;

public class Nave {

    private Vector3 position;
    private Vector3 velocity;
    private ColisaoHandler gerenciadorColisao;

    Texture naveReta;
    NaveAnimation nave = new NaveAnimation();

    public Nave(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, Constantes.NAVE_VELOCIDADE, 0);
        naveReta = new Texture("nave\\reto.png");
        gerenciadorColisao = new ColisaoHandler();
        gerenciadorColisao.add( new NaveColisao(position, 21, 14, 5, 0));
        gerenciadorColisao.add( new NaveColisao(position, 7, 29 ,12 , 0));
    }

    private boolean isLimiteScreen(OrthographicCamera camera){
        if (isLimiteEsquerdo(camera)){
            return true;
        }
        if (isLimiteDireito(camera)){
            return true;
        }
        Constantes.printarRelatorio("false");
        return false;
    }

    private boolean isLimiteDireito(OrthographicCamera camera){
        if (position.x >= camera.position.x + (camera.viewportWidth /2) - naveReta.getWidth()/2){
            return true;
        }
        return false;
    }

    private boolean isLimiteEsquerdo(OrthographicCamera camera){
        if (position.x <= camera.position.x - camera.viewportWidth /2){
            Constantes.printarRelatorio("true");
            return true;
        }
        return false;
    }

    public void update(float dt, OrthographicCamera camera){
        if (isLimiteDireito(camera)){
            if (velocity.x >0){
                position.add(0, velocity.y, 0);
            }else{
                position.add(velocity.x, velocity.y, 0);
            }
        }else if (isLimiteEsquerdo(camera)){
            if (velocity.x < 0){
                position.add(0,velocity.y,0);
            }else{
                position.add(velocity.x,velocity.y,0);
            }
        }else {
            position.add(velocity.x,velocity.y,0);
        }


        nave.updateAll(dt);
        reduzEscalaVelocidadeLateral();
        gerenciadorColisao.updateAll(position);
    }

    private void reduzEscalaVelocidadeLateral(){
        if (velocity.x > 0 ){
            velocity.x -= 0.5f;
        }if (velocity.x < 0){
            velocity.x += 0.5f;
        }
    }

    public Tiro atirar() {
        return new Tiro(position.x+(naveReta.getWidth()/4)-3, position.y);
    }

    public void praEsquerda(){
        velocity.x -=1;
        //position.x -= 4;
    }

    public void praDireita(){
        velocity.x +=1;
        //position.x += 4;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public TextureRegion getNave() {
        return nave.getByPosition(velocity).getFrame();
    }

    public void dispose(){
        //nave_reta.dispose();
        position = null;
        velocity = null;
    }

    public ColisaoHandler getGerenciadorColisao() {
        return gerenciadorColisao;
    }
}
