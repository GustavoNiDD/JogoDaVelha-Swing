import java.awt.Color;

import javax.swing.JButton;

public class campos extends JButton {
    int id;
    String nome;
    boolean status;

    public campos() {

    }

    public campos(String nome, boolean status, int id) {
        this.id = id;
        this.nome = nome;
        this.id = id;
        setBackground(Color.LIGHT_GRAY);
        setForeground(Color.WHITE);
        setVisible(true);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
