package principal;


	import java.util.ArrayList;

	public class jogador {
	    private ArrayList<carta> mao;
	    private int pontuacao;

	    public jogador() {
	        mao = new ArrayList<>();
	        pontuacao = 0;
	    }

	    public void receberCarta(carta carta) {
	        mao.add(carta);
	        pontuacao += carta.getPontuacao();
	        ajustarAs();
	    }

	    private void ajustarAs() {
	        if (pontuacao > 21) {
	            for (carta carta : mao) {
	                if (carta.getValor().equals("Ás") && pontuacao > 21) {
	                    pontuacao -= 10;
	                }
	            }
	        }
	    }

	    public int getPontuacao() {
	        return pontuacao;
	    }

	    public ArrayList<carta> getMao() {
	        return mao;
	    }

	    public void resetar() {
	        mao.clear();
	        pontuacao = 0;
	    }
	}


