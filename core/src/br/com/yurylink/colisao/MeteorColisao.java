package br.com.yurylink.colisao;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.utils.Box2DBuild;

public class MeteorColisao extends ColisaoAbstract{

    private Circle limite;

    public MeteorColisao(Vector3 posicao, float width, float height, float offsetX, float offsetY){
        super(offsetX, offsetY);
        limite = new Circle(posicao.x+(width/2), posicao.y+(height/2), width/2);
    }

    @Override
    public void update(Vector3 posicao) {
        limite.setPosition(posicao.x+getOffsetX(), posicao.y+getOffsetY());
    }

    @Override
    public Rectangle getBlocoColisao() {
        return null;
    }

    @Override
    public Circle getCirculoColisao() {
        return limite;
    }

}
