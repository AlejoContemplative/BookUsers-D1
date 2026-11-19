import javax.swing.*;

public class FormCreditos extends JFrame {
    private JPanel panelPrincipal;
    private JTextArea txtCreditos;

    public FormCreditos() {
        setTitle("BookUsers · Créditos");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);

        txtCreditos.setEditable(false);
        txtCreditos.setText(
                "Alejandro Arenas Bustamante\n"
                        + "Código: (código de Alejandro)\n"
                        + "Correo: (correo de Alejandro)\n\n"
                        + "Ariana Albarran (tu segundo apellido)\n"
                        + "Código: (tu código)\n"
                        + "Correo: (tu correo)");
    }
}