import javax.swing.*;

public class FormMenu extends JFrame {
    private JButton btnRegistro;
    private JButton btnListado;
    private JButton btnCreditos;
    private JPanel panelPrincipal;

    // el form es lo grafico, el java es el codigo

    public FormMenu(){
        setTitle("BookUsers - Alejandro Arenas");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args){
        FormMenu menu = new FormMenu();
        menu.setVisible(true);
    }
}
