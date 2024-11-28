package principal;

import java.util.ArrayList;
import java.util.Collections;

public class baralho {
    private ArrayList<carta> cartas;

    public baralho() {
        cartas = new ArrayList<>();
        String[] valores = {"Ás", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valete", "Dama", "Rei"};
        String[] naipes = {"Copas", "Ouros", "Espadas", "Paus"};
        int[] pontuacoes = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

        for (String naipe : naipes) {
            for (int i = 0; i < valores.length; i++) {
                cartas.add(new carta(valores[i], naipe, pontuacoes[i]));
            }
        }

        embaralhar();
    }

    public void embaralhar() {
        Collections.shuffle(cartas);
    }

    public carta distribuir() {
        return cartas.remove(0);
    }
}


