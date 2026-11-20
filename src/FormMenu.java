import javax.swing.*;
import java.util.ArrayList;

public class FormMenu extends JFrame {
    private JButton btnRegistro;
    private JButton btnListado;
    private JButton btnCreditos;
    private JPanel panelPrincipal;
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    // el form es lo grafico, el java es el codigo

    public FormMenu(){
        setTitle("BookUsers - Alejandro Arenas");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        btnCreditos.addActionListener(e -> {
            FormCreditos creditos = new FormCreditos();
            creditos.setVisible(true);
        });

        btnRegistro.addActionListener(e -> {
            FormRegistro registro = new FormRegistro(usuarios);
            registro.setVisible(true);
        });

        btnListado.addActionListener(e -> {
            FormListado listado = new FormListado(usuarios);
            listado.setVisible(true);
        });
    }

    public static void main(String[] args){
        FormMenu menu = new FormMenu();
        menu.setVisible(true);
    }
}