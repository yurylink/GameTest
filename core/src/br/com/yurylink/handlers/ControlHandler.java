package br.com.yurylink.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import br.com.yurylink.game.configurations.util.Constantes;
import br.com.yurylink.sprites.Nave;

public class ControlHandler {

    private Texture upBtn;
    private Texture leftBtn;
    private Texture rightBtn;

    public ControlHandler(){
        this.leftBtn = new Texture("direcionais\\left32.png");
        this.rightBtn = new Texture("direcionais\\right32.png");
        this.upBtn = new Texture("direcionais\\up32.png");
    }

    public void reconheceControleTouchpad(OrthographicCamera camera, Nave nave, TirosHandler tirosHandler){
        if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(touchPos);

            if (touchPos.y < nave.getPosition().y+50){
                if (touchPos.x < 100){
                    nave.praEsquerda();
                }
                if (touchPos.x > 100){
                    nave.praDireita();
                }
            }else{
                tirosHandler.add(nave.atirar(), System.currentTimeMillis());
            }
            Constantes.printarRelatorio("X: " + touchPos.x + ", Y: " + touchPos.y);
        }
    }

    public void reconheceControleTeclado(Nave nave, TirosHandler tirosHandler){
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            nave.praDireita();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            nave.praEsquerda();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            tirosHandler.add(nave.atirar(), System.currentTimeMillis());
        }
    }

    public void reconheceControleTouchpad2(OrthographicCamera camera, Nave nave, TirosHandler tirosHandler){
        if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(touchPos);

            if (touchPos.x > 0 && touchPos.x <= 80){
                nave.praEsquerda();
            }else if (touchPos.x >=81 && touchPos.x <=160){
                tirosHandler.add(nave.atirar(), System.currentTimeMillis());
            }else if (touchPos.x >160){
                nave.praDireita();
            }
            Constantes.printarRelatorio("X: " + touchPos.x + ", Y: " + touchPos.y);
        }
    }

    public void desenharBotoes(SpriteBatch sb, OrthographicCamera camera){
        sb.draw(leftBtn, 24, (camera.position.y-camera.viewportHeight/2)+24);
        sb.draw(upBtn, (camera.viewportWidth/2)-upBtn.getWidth()/2, (camera.position.y-camera.viewportHeight/2)+10);
        sb.draw(rightBtn, 184, (camera.position.y-camera.viewportHeight/2)+24);
    }
}
