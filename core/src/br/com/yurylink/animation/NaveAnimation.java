package br.com.yurylink.animation;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class NaveAnimation {


    private static final int ESQUERDA_MAX = 0;
    private static final int ESQUERDA_MED = 1;
    private static final int MEIO = 2;
    private static final int DIREITA_MED = 3;
    private static final int DIREITA_MAX = 4;

    private List<Animation> animacoes;

    public NaveAnimation(){
        animacoes = new ArrayList<Animation>();

        Texture naveReta = new Texture("nave\\reto.png");
        Texture nameEsqMed = new Texture("nave\\Esquerda_med.png");
        Texture naveEsqMax = new Texture("nave\\Esquerda_max.png");
        Texture naveDirMax = new Texture("nave\\Direita_max.png");
        Texture naveDirMed = new Texture("nave\\Direita_med.png");

        Animation nave_max_esquerda = new Animation(new TextureRegion(naveEsqMax),2,0.1f);
        Animation nave_med_esquerda = new Animation(new TextureRegion(nameEsqMed),2,0.1f);
        Animation nave_reta = new Animation(new TextureRegion(naveReta),2,0.1f);
        Animation nave_med_direita = new Animation(new TextureRegion(naveDirMed),2,0.1f);
        Animation nave_max_direita = new Animation(new TextureRegion(naveDirMax),2,0.1f);

        animacoes.add(ESQUERDA_MAX, nave_max_esquerda);
        animacoes.add(ESQUERDA_MED, nave_med_esquerda);
        animacoes.add(MEIO, nave_reta);
        animacoes.add(DIREITA_MED, nave_med_direita);
        animacoes.add(DIREITA_MAX, nave_max_direita);
    }

    public Animation getAnimation(int index){
        return animacoes.get(index);
    }

    public Animation getByPosition(Vector3 velocidade){

        if (velocidade.x > 2.0){
            return animacoes.get(DIREITA_MAX);
        }
        if (velocidade.x < -2.0){
            return  animacoes.get(ESQUERDA_MAX);
        }
        if (velocidade.x > 0.0){
            return animacoes.get(DIREITA_MED);
        }
        if (velocidade.x < 0.0){
            return animacoes.get(ESQUERDA_MED);
        }
        return animacoes.get(MEIO);
    }

    public void updateAll(float dt){
        for (Animation a:animacoes) {
            a.update(dt);
        }
    }
	
}
