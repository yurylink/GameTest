package br.com.yurylink.handlers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.com.yurylink.game.configurations.util.Constantes;
import br.com.yurylink.sprites.Explosao;

public class ExplosaoHandler {
	

    private List<Explosao> listaExplosao;

    public ExplosaoHandler(){
        listaExplosao = new ArrayList<Explosao>();
    }

    public void add(Explosao e){
        if (e!=null){
            listaExplosao.add(e);
        }
    }

    public void desenhaExplosoes(SpriteBatch sb){
        for (int i=0 ; i< listaExplosao.size(); i++){
            Explosao e= listaExplosao.get(i);
            if(e.getTexturas().size()>e.getControle()){
                sb.draw(e.getTexturas().get(e.getControle()),e.getPosicao().x,e.getPosicao().y);
                if (System.currentTimeMillis()> e.getDeltaTimeInterno() + 500l){
                    e.incrementaControle();
                }
            }else{
                Constantes.printarRelatorio("removido");
                listaExplosao.remove(e);
                e.dispose();
            }
        }
    }

    public void updateList(float dt){
        for (Explosao e:listaExplosao){
            e.update(dt);
        }
    }

}
