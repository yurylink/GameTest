package br.com.yurylink.handlers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.com.yurylink.game.configurations.util.Constantes;
import br.com.yurylink.sprites.Tiro;

public class TirosHandler {
	
    private List<Tiro> listaTiros;

    public Long deltaTime;

    public TirosHandler(){
        listaTiros = new ArrayList<Tiro>();
        deltaTime = 0l;
    }

    public void add(Tiro tiro, Long timeMiliSeconds){
        if(deltaTime.equals(0l)){
            deltaTime = timeMiliSeconds;
            tiro.getSound().play(0.3f);
            listaTiros.add(tiro);
        }else if (timeMiliSeconds - deltaTime >= Constantes.TIROS_TIME_GAP){
            deltaTime = timeMiliSeconds;
            tiro.getSound().play(0.3f);
            listaTiros.add(tiro);
        }
    }

    public void desenhaTiros(SpriteBatch sb){
        for (Tiro tiro:listaTiros) {
            sb.draw(tiro.getTextura(), tiro.getPosicao().x, tiro.getPosicao().y);
        }
    }

    public void atualizaTiros(float dt){
        for (Tiro tiro:listaTiros) {
            tiro.update(dt);
        }
    }

    public void removerTirosOffScreen(OrthographicCamera camera){
        if (!listaTiros.isEmpty()){
            for (int i = 0; i < listaTiros.size(); i++){
                Tiro tiro = listaTiros.get(i);
                if(tiro.getPosicao().y > camera.position.y + ((camera.viewportHeight/2) + tiro.getTextura().getHeight())){
                    listaTiros.remove(tiro);
                    tiro.dispose();
                    Constantes.printarRelatorio("tiro removido");
                }
            }
        }
    }

    public void disposeAll(){
        for (Tiro tiro:listaTiros){
        tiro.dispose();
    }
}

    public List<Tiro> getLista(){
        return listaTiros;
    }
}
