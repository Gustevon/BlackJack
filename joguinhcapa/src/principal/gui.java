package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui extends JFrame {
    private jogador jogador;
    private jogador dealer;
    private baralho baralho;

    private JTextArea areaCartasJogador, areaCartasDealer;
    private JLabel labelPontuacaoJogador, labelPontuacaoDealer;

    public gui() {
        setTitle("Blackjack");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        jogador = new jogador();
        dealer = new jogador();
        baralho = new baralho();

        // Painel superior (dealer)
        JPanel painelDealer = new JPanel(new BorderLayout());
        areaCartasDealer = new JTextArea(5, 30);
        areaCartasDealer.setEditable(false);
        labelPontuacaoDealer = new JLabel("Pontuação do Dealer: 0");
        painelDealer.add(new JScrollPane(areaCartasDealer), BorderLayout.CENTER);
        painelDealer.add(labelPontuacaoDealer, BorderLayout.SOUTH);
        add(painelDealer, BorderLayout.NORTH);

        // Painel inferior (jogador)
        JPanel painelJogador = new JPanel(new BorderLayout());
        areaCartasJogador = new JTextArea(5, 30);
        areaCartasJogador.setEditable(false);
        labelPontuacaoJogador = new JLabel("Pontuação do Jogador: 0");
        painelJogador.add(new JScrollPane(areaCartasJogador), BorderLayout.CENTER);
        painelJogador.add(labelPontuacaoJogador, BorderLayout.SOUTH);
        add(painelJogador, BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel();
        JButton btnPedir = new JButton("Pedir Carta");
        JButton btnParar = new JButton("Parar");
        painelBotoes.add(btnPedir);
        painelBotoes.add(btnParar);
        add(painelBotoes, BorderLayout.SOUTH);

        // Eventos
        btnPedir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carta carta = baralho.distribuir();
                jogador.receberCarta(carta);
                atualizarInterface();

                if (jogador.getPontuacao() > 21) {
                    JOptionPane.showMessageDialog(null, "Você estourou! Dealer venceu.");
                    reiniciarJogo();
                }
            }
        });

        btnParar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (dealer.getPontuacao() < 17) {
                    dealer.receberCarta(baralho.distribuir());
                }
                atualizarInterface();
                verificarVencedor();
            }
        });

        iniciarJogo();
    }

    private void iniciarJogo() {
        jogador.resetar();
        dealer.resetar();
        baralho = new baralho();
        areaCartasJogador.setText("");
        areaCartasDealer.setText("");
        labelPontuacaoJogador.setText("Pontuação do Jogador: 0");
        labelPontuacaoDealer.setText("Pontuação do Dealer: 0");

        jogador.receberCarta(baralho.distribuir());
        jogador.receberCarta(baralho.distribuir());
        dealer.receberCarta(baralho.distribuir());

        atualizarInterface();
    }

    private void atualizarInterface() {
        areaCartasJogador.setText(jogador.getMao().toString());
        areaCartasDealer.setText(dealer.getMao().toString());
        labelPontuacaoJogador.setText("Pontuação do Jogador: " + jogador.getPontuacao());
        labelPontuacaoDealer.setText("Pontuação do Dealer: " + dealer.getPontuacao());
    }

    private void verificarVencedor() {
        int pontosJogador = jogador.getPontuacao();
        int pontosDealer = dealer.getPontuacao();

        if (pontosDealer > 21 || pontosJogador > pontosDealer) {
            JOptionPane.showMessageDialog(null, "Você venceu!");
        } else if (pontosJogador == pontosDealer) {
            JOptionPane.showMessageDialog(null, "Empate!");
        } else {
            JOptionPane.showMessageDialog(null, "Dealer venceu!");
        }

        reiniciarJogo();
    }

    private void reiniciarJogo() {
        iniciarJogo();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new gui().setVisible(true));
    }
}

