import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class tabuleiro extends JFrame implements ActionListener {
    int turno = 0;
    int numeroCampos = 9;
    ArrayList<Integer> listaJogador1 = new ArrayList<>();
    ArrayList<Integer> listaJogador2 = new ArrayList<>();
    ArrayList<campos> reset = new ArrayList<>();
    String nomeJogador1 = JOptionPane.showInputDialog("Digite o nome do jogador 1 (X)");
    String nomeJogador2 = JOptionPane.showInputDialog("Digite o nome do jogador 2 (O)");
    jogador jogador1 = new jogador(nomeJogador1, listaJogador1);
    jogador jogador2 = new jogador(nomeJogador2, listaJogador1);

    public void definirNome() {
        if (nomeJogador1 == null) {
            jogador1.nome = "Jogador 1";
        }
        if (nomeJogador2 == null) {
            jogador2.nome = "Jogador 2";
        }
    }

    public void quemComeca() {
        String[] continuar = { "X", "O" };
        String quem = (String) JOptionPane.showInputDialog(null, "Quem começa dessa vez?",
                "Quem começa dessa vez", JOptionPane.QUESTION_MESSAGE, null, continuar, continuar[0]);
        if (quem == null) {
            int range = 2 - 1 + 1;
            int valor = (int) (Math.random() * range) + 1;
            if(valor == 2){
                JOptionPane.showMessageDialog(null, "O Jogador 1 (X) começa");
                turno = 0;
            }else{
                JOptionPane.showMessageDialog(null, "O Jogador 2 (O) começa");
                turno = 1;
            }
        } else if (quem.equals("X")) {
            turno = 0;
        } else {
            turno = 1;
        }
    }

    public void criarTela() {
        definirNome();
        quemComeca();
        GridLayout layout = new GridLayout(3, 3, ALLBITS, ABORT);
        layout.setHgap(0);
        layout.setVgap(0);

        JFrame tela = new JFrame("Jogo da velha by: Gustavo Garcia Santana");
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tela.setLayout(layout);
        tela.setVisible(true);
        tela.setSize(600, 600);

        for (int i = 0; i < 9; i++) {
            campos campo = new campos("", false, i);
            reset.add(campo);
            campo.addActionListener(this);
            tela.add(campo);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton) {
            campos campo = (campos) e.getSource();
            if (campo.status == false) {
                campo.setStatus(true);
                numeroCampos--;
                if (turno == 0) {
                    campo.setBackground(new Color(145, 1, 1));
                    campo.setText("X");
                    listaJogador1.add(campo.id);
                    turno = 1;
                    condicaoVitoria();
                } else {
                    campo.setBackground(new Color(1, 1, 145));
                    campo.setText("O");
                    listaJogador2.add(campo.id);
                    turno = 0;
                    condicaoVitoria();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Campo já selecionado");
            }

            empate();
        }
    }

    public void condicaoVitoria() {

        List<Integer> condicaoH1 = Arrays.asList(0, 1, 2);
        List<Integer> condicaoH2 = Arrays.asList(3, 4, 5);
        List<Integer> condicaoH3 = Arrays.asList(6, 7, 8);
        List<Integer> condicaoV1 = Arrays.asList(0, 3, 6);
        List<Integer> condicaoV2 = Arrays.asList(1, 4, 7);
        List<Integer> condicaoV3 = Arrays.asList(2, 5, 8);
        List<Integer> condicaoD1 = Arrays.asList(0, 4, 8);
        List<Integer> condicaoD2 = Arrays.asList(2, 4, 6);

        if (listaJogador1.containsAll(condicaoH1) || listaJogador1.containsAll(condicaoH2)
                || listaJogador1.containsAll(condicaoH3) || listaJogador1.containsAll(condicaoV1)
                || listaJogador1.containsAll(condicaoV2) || listaJogador1.containsAll(condicaoV3)
                || listaJogador1.containsAll(condicaoD1) || listaJogador1.containsAll(condicaoD2)) {
            JOptionPane.showMessageDialog(null, jogador1.nome + " X ganhou");

            String[] continuar = { "sim", "não" };
            String n = (String) JOptionPane.showInputDialog(null, "Quer continuar?",
                    "Quer continuar?", JOptionPane.QUESTION_MESSAGE, null, continuar, continuar[0]);

            if (n == null) {
                continuar("não");
            } else {
                continuar(n);
            }

        } else if (listaJogador2.containsAll(condicaoH1) || listaJogador2.containsAll(condicaoH2)
                || listaJogador2.containsAll(condicaoH3) || listaJogador2.containsAll(condicaoV1)
                || listaJogador2.containsAll(condicaoV2) || listaJogador2.containsAll(condicaoV3)
                || listaJogador2.containsAll(condicaoD1) || listaJogador2.containsAll(condicaoD2)) {
            JOptionPane.showMessageDialog(null, jogador2.nome + " O ganhou");

            String[] continuar = { "sim", "não" };
            String n = (String) JOptionPane.showInputDialog(null, "Quer continuar?",
                    "Quer continuar?", JOptionPane.QUESTION_MESSAGE, null, continuar, continuar[0]);

            if (n == null) {
                continuar("não");
            } else {
                continuar(n);
            }

        }
    }

    public void empate() {
        if (numeroCampos == 0) {
            JOptionPane.showMessageDialog(null, "Empate xD");
        }
    }

    public void continuar(String n) {
        if (n.equals("sim")) {

            quemComeca();

            for (campos campo : reset) {
                campo.setText("");
                campo.setStatus(false);
                campo.setBackground(Color.LIGHT_GRAY);
            }
            numeroCampos = 9;
            listaJogador1.clear();
            listaJogador2.clear();

        } else if (n.equals("não")) {
            JOptionPane.showMessageDialog(null, " Obrigado por jogar");
            System.exit(0);
        }
    }

}
