package br.com.yurylink.handlers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import br.com.yurylink.colisao.ColisaoAbstract;
import br.com.yurylink.colisao.MeteorColisao;

public class MeteorAbstract {
    private Vector3 posicao;
    private Vector3 velocidade;
    private Texture textura;
    private ColisaoHandler gerenciardorColisao;

    public MeteorAbstract(float x, float y, String imagemTextura){
        textura = new Texture(imagemTextura);
        posicao = new Vector3(x,y,0);
        velocidade = new Vector3(0,0,0);
        gerenciardorColisao = new ColisaoHandler();
        gerenciardorColisao.add(new MeteorColisao(posicao, textura.getWidth(), textura.getHeight(),0,0));
    }

    public void update(float dt){
        gerenciardorColisao.updateAll(posicao);
    }

    public boolean isColisao(Rectangle obj){
        for (ColisaoAbstract c:gerenciardorColisao.getLista()) {
//            if(obj.overlaps(c.getCirculoColisao()){//TODO: corrigir problema de colsisão com circulo
//                return true;
//            }
        }
        return false;
    }

    public boolean isColisao(Circle obj){
        for (ColisaoAbstract c:gerenciardorColisao.getLista()) {
            if(obj.overlaps(c.getCirculoColisao())){
                return true;
            }
        }
        return false;
    }

    public ColisaoHandler getGerenciadorColisao(){
        return gerenciardorColisao;
    }

    public Vector3 getPosicao() {
        return posicao;
    }

    public Vector3 getVelocidade() {
        return velocidade;
    }

    public Texture getTextura() {
        return textura;
    }

    public void dispose() {
        posicao =null;
        velocidade = null;
        textura = null;
    }
}
