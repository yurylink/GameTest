package br.com.yurylink.handlers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import br.com.yurylink.colisao.ColisaoAbstract;

public class ColisaoHandler {

    public static final Integer CIRCULO = 1;
    public static final Integer RECTANGLE = 2;

    List<ColisaoAbstract> listaDeLimites;

    public ColisaoHandler(){
        listaDeLimites = new ArrayList<ColisaoAbstract>();
    }

    public void add(ColisaoAbstract colisao){
        listaDeLimites.add(colisao);
    }

    public void updateAll(Vector3 position){
        for (ColisaoAbstract c:listaDeLimites) {
            c.update(position);
        }
    }

    public List<ColisaoAbstract> getLista(){
        return listaDeLimites;
    }

    public void desenharLimites(ShapeRenderer shapeDrawer, OrthographicCamera camera, Integer formato){
        shapeDrawer.setAutoShapeType(true);
        shapeDrawer.begin();
        shapeDrawer.set(ShapeRenderer.ShapeType.Line);
        shapeDrawer.setProjectionMatrix(camera.combined);

        for (ColisaoAbstract c: listaDeLimites) {
            if (formato == RECTANGLE){
                shapeDrawer.rect(c.getBlocoColisao().x, c.getBlocoColisao().y, c.getBlocoColisao().width, c.getBlocoColisao().height);
            }/*else {
//                shapeDrawer.circle(c.getBlocoColisao().x+(c.getBlocoColisao().width/2), c.getBlocoColisao().y+(c.getBlocoColisao().height/2), c.getBlocoColisao().width/2);
            }*/

    }
        shapeDrawer.end();
    }
}
