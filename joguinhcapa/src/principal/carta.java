package principal;

public class carta {



		    private String valor;
		    private String naipe;
		    private int pontuacao;

		    public carta(String valor, String naipe, int pontuacao) {
		        this.valor = valor;
		        this.naipe = naipe;
		        this.pontuacao = pontuacao;
		    }

		    public String getValor() {
		        return valor;
		    }

		    public String getNaipe() {
		        return naipe;
		    }

		    public int getPontuacao() {
		        return pontuacao;
		    }

		    @Override
		    public String toString() {
		        return valor + " de " + naipe;
		    
		

	}

}
