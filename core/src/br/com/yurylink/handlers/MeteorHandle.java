package br.com.yurylink.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import br.com.yurylink.colisao.ColisaoAbstract;
import br.com.yurylink.game.configurations.util.Constantes;
import br.com.yurylink.sprites.Explosao;
import br.com.yurylink.sprites.MeteoroPequenoAleatorio;
import br.com.yurylink.sprites.Tiro;

public class MeteorHandle {

    private static final Random RANDOM = new Random();

    private List<MeteorAbstract> listaMeteoros;
    public Long deltaTime;
    private Long pontuacao;

    public MeteorHandle(){
        listaMeteoros = new ArrayList<MeteorAbstract>();
        deltaTime = 0l;
        pontuacao = 0l;
    }

    public void gerarMeteoro(OrthographicCamera camera){
        if(RANDOM.nextBoolean()){
//            add(new MeteoroGrande(RANDOM.nextInt(235),
//                            (Constantes.GAME_HEIGHT/2) + camera.position.y),
//                            System.currentTimeMillis());        }
            add(new MeteoroPequenoAleatorio(RANDOM.nextInt(220),
                    (Constantes.GAME_HEIGHT/2) + camera.position.y),
                    System.currentTimeMillis(), pontuacao * 100);}
    }

    public void add(MeteorAbstract meteoro, Long timeMiliSeconds, Long modificadorDificuldade){
        if(deltaTime.equals(0l)){
            deltaTime = timeMiliSeconds;
            listaMeteoros.add(meteoro);
        }else if (timeMiliSeconds- deltaTime >= (Constantes.METEOROS_TIME_GAP - modificadorDificuldade) + 500l ){
            Constantes.printarRelatorio("modificador" + (Constantes.METEOROS_TIME_GAP - modificadorDificuldade) + 500l);
            Constantes.printarRelatorio("time:" + timeMiliSeconds + "// delta: " + deltaTime);
            deltaTime = timeMiliSeconds;
            listaMeteoros.add(meteoro);
        }
    }

    public void desenharLimitesMeteoro(ShapeRenderer shapeRenderer, OrthographicCamera camera){
        for (MeteorAbstract m: listaMeteoros) {
            m.getGerenciadorColisao().desenharLimites(shapeRenderer,camera, ColisaoHandler.CIRCULO);
        }
    }

    public boolean verificarColisao(List<ColisaoAbstract> obj){
        for (MeteorAbstract meteoro:listaMeteoros){
            for (ColisaoAbstract r:obj) {
            	if(r.getBlocoColisao() != null){
            		if (meteoro.isColisao(r.getBlocoColisao())){
                        return true;
                    }
            	}
            	if(r.getCirculoColisao() != null){
            		if (meteoro.isColisao(r.getCirculoColisao())){
            			return true;
            		}
            	}
                
            }
        }
        return false;
    }

    public Explosao handleColisionTiro(List<Tiro> tiros){
        for (int t = 0; t < tiros.size(); t++){
            Tiro tiro = tiros.get(t);
            for (int i=0; i<listaMeteoros.size(); i++){
                MeteorAbstract meteoro = listaMeteoros.get(i);
                if (meteoro.isColisao(tiro.getLimite())){
                    listaMeteoros.remove(meteoro);
                    tiros.remove(tiro);
                    tiro.dispose();
                    Explosao x= new Explosao(meteoro.getPosicao().x, meteoro.getPosicao().y);
                    meteoro.dispose();
                    pontuacao ++;
                    return x;
                }
            }
        }
        return null;
    }

    public void atualizarMeteoros(float dt){
        for (MeteorAbstract meteoro:listaMeteoros){
            meteoro.update(dt);
        }
    }

    public void desenharMeteoro(SpriteBatch sb){
        for (MeteorAbstract meteoro:listaMeteoros){
            sb.draw(meteoro.getTextura(), meteoro.getPosicao().x, meteoro.getPosicao().y);
        }
    }

    public void removerObjOffScreen(OrthographicCamera camera){
        for (int i=0; i < listaMeteoros.size() ; i++){
            MeteorAbstract meteoro = listaMeteoros.get(i);
            if (meteoro.getPosicao().y < camera.position.y - (camera.viewportHeight/2 + meteoro.getTextura().getHeight())){
                listaMeteoros.remove(meteoro);
                meteoro.dispose();
                Constantes.printarRelatorio("removido meteoro");
            }
        }
    }

    public void disposeAll(){
        for (MeteorAbstract meteoro:listaMeteoros){
            meteoro.dispose();
        }
    }

    public List<MeteorAbstract> getLista(){
        return listaMeteoros;
    }

    public Long getPontuacao() {
        return pontuacao;
    }
}
