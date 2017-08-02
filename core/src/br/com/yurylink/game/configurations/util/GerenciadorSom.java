package br.com.yurylink.game.configurations.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

public final class GerenciadorSom {
	

    private static GerenciadorSom INSTANCE;
    private static AssetManager assetManager;

    private Sound tiro;
    private Sound explosaoPequena;

    private GerenciadorSom(){
        tiro = Gdx.audio.newSound(Gdx.files.internal("tiro2.wav"));
        explosaoPequena = Gdx.audio.newSound(Gdx.files.internal("expPequena.ogg"));
    }

    public static GerenciadorSom getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GerenciadorSom();
        }
        return INSTANCE;
    }

    public Sound getTiro() {
        return tiro;
    }

    public Sound getExplosaoPequena() {
        return explosaoPequena;
    }
}
