package br.com.yurylink.colisao;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public abstract class ColisaoAbstract {
	
    private float offsetX;
    private float offsetY;

    protected ColisaoAbstract(float offsetX, float offsetY){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public abstract void  update(Vector3 posicao);

    public abstract Rectangle getBlocoColisao();

    public abstract Circle getCirculoColisao();

    protected float getOffsetX() {
        return offsetX;
    }

    protected float getOffsetY() {
        return offsetY;
    }
}
