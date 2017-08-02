package br.com.yurylink.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import br.com.yurylink.game.configurations.util.Constantes;
import br.com.yurylink.game.configurations.util.GerenciadorSom;

public class Tiro {

    private Vector3 posicao;
    private Vector3 velocidade;
    private Texture textura;
    private Rectangle limite;
    private GerenciadorSom gerenciadorSom;

    public Tiro(float x, float y){
        textura = new Texture("tiro.png");
        posicao = new Vector3(x, y+10, 0);
        velocidade = new Vector3(0,Constantes.VELOCIDADE_TIRO_INICIAL,0);
        limite = new Rectangle(posicao.x, posicao.y, textura.getWidth(), textura.getHeight());
        gerenciadorSom = GerenciadorSom.getInstance();
    }

    public void update(float dt){
        velocidade.add(0, Constantes.NAVE_VELOCIDADE,0);
        velocidade.scl(dt);
        posicao.add(0,Constantes.NAVE_VELOCIDADE,0);
        posicao.add(velocidade);
        velocidade.scl(1/dt);
        limite.setPosition(posicao.x, posicao.y);
    }

    public Texture getTextura() {
        return textura;
    }

    public Vector3 getPosicao() {
        return posicao;
    }

    public void dispose() {
        textura.dispose();
        posicao = null;
        velocidade = null;
    }

    public Rectangle getLimite() {
        return limite;
    }

    public Sound getSound() {
        return gerenciadorSom.getTiro();
    }
}
