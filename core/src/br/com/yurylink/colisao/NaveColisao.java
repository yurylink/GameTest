package br.com.yurylink.colisao;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class NaveColisao extends ColisaoAbstract{

    private Rectangle limite;

    public NaveColisao(Vector3 posicao, float width, float height, float offsetX, float offsetY){
        super(offsetX, offsetY);
        limite = new Rectangle(posicao.x+getOffsetX(), posicao.y+getOffsetY(), width, height);
    }

    public void update(Vector3 posicao){
        limite.setPosition(posicao.x+getOffsetX(), posicao.y + getOffsetY());
    }

    public Rectangle getBlocoColisao(){
        return limite;
    }

    @Override
    public Circle getCirculoColisao() {
        return null;
    }
}
