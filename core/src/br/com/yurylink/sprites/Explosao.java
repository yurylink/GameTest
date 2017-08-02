package br.com.yurylink.sprites;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import br.com.yurylink.game.configurations.util.GerenciadorSom;

public class Explosao {

    private List<Texture> texturas;
    private Vector3 posicao;
    private Vector3 velocidade;
    private int controle;
    private long deltaTimeInterno;
    private GerenciadorSom gerenciadorSom;

    public Explosao(float x, float y){
        texturas = inicializarTexturas();
        posicao = new Vector3(x, y, 0);
        velocidade = new Vector3(0,0,0);
        controle = 0;
        deltaTimeInterno =0l;
        gerenciadorSom = GerenciadorSom.getInstance();
        gerenciadorSom.getExplosaoPequena().play();
    }

    private List<Texture> inicializarTexturas(){
        List<Texture> t = new ArrayList<Texture>();
        for (int i =0 ; i<=26; i++){
            t.add(i,new Texture("explosao\\x"+ i + ".png"));
        }
        return t;
    }

    public void update(float dt){

    }

    public void incrementaControle(){
        controle ++;
    }

    public void dispose(){
        texturas = null;
        posicao = null;
        velocidade = null;
        controle = 0;
        //sound.dispose();
    }

    public List<Texture> getTexturas() {
        return texturas;
    }

    public Vector3 getPosicao() {
        return posicao;
    }

    public Vector3 getVelocidade() {
        return velocidade;
    }

    public int getControle() {
        return controle;
    }

    public long getDeltaTimeInterno() {
        return deltaTimeInterno;
    }

    public void setDeltaTimeInterno(long deltaTimeInterno) {
        this.deltaTimeInterno = deltaTimeInterno;
    }
}
