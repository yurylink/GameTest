package br.com.yurylink.sprites;

import java.util.Random;

import br.com.yurylink.handlers.MeteorAbstract;

public class MeteoroPequenoAleatorio extends MeteorAbstract{
    private static final Random RANDOM = new Random();
    private static final String[] listaTexturaMeteoro = {"meteoro_grande.png","meteoro_p_1.png","meteoro_p_2.png","meteoro_p_3.png"};


    public MeteoroPequenoAleatorio(float x, float y) {
        super(x, y, listaTexturaMeteoro[RANDOM.nextInt(4)]);
    }
}
