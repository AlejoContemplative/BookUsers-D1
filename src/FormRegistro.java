import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.text.Normalizer;
import java.util.ArrayList;

public class FormRegistro extends JFrame {
    private JPanel panelPrincipal;
    private JComboBox<String> cmbTipoId;
    private JTextField txtNumeroId;
    private JTextField txtPrimerNombre;
    private JTextField txtSegundoNombre;
    private JTextField txtPrimerApellido;
    private JTextField txtSegundoApellido;
    private JLabel lblUsername;
    private JTextField txtCorreo;
    private JButton btnCargarFoto;
    private JLabel lblFoto;
    private JButton btnGuardar;
    private JTextField txtTelefono;
    private JPasswordField pwdPassword;

    private ArrayList<Usuario> usuarios;

    public FormRegistro(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
        setTitle("BookUsers · Registro");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 550);
        setLocationRelativeTo(null);
        lblUsername.setText("");
        llenarTipos();
        activarUsername();
    }

    private void llenarTipos() {
        String[] tipos = {
                "11 - Registro civil de nacimiento",
                "12 - Tarjeta de identidad",
                "13 - Cédula de ciudadanía",
                "21 - Tarjeta de extranjería",
                "22 - Cédula de extranjería",
                "41 - Pasaporte",
                "42 - Documento de identificación extranjero",
                "47 - Permiso Especial de Permanencia (PEP)",
                "91 - NUIP"
        };
        for (String t : tipos) {
            cmbTipoId.addItem(t);
        }
    }

    private String quitarTildes(String texto) {
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return normalizado.replaceAll("\\p{M}", "");
    }

    private String generarUsername() {
        String nombre = quitarTildes(txtPrimerNombre.getText().trim());
        String apellido1 = quitarTildes(txtPrimerApellido.getText().trim());
        String apellido2 = quitarTildes(txtSegundoApellido.getText().trim());

        String inicial1 = "";
        if (!apellido1.isEmpty()) {
            inicial1 = apellido1.substring(0, 1);
        }
        String inicial2 = "";
        if (!apellido2.isEmpty()) {
            inicial2 = apellido2.substring(0, 1);
        }

        return (inicial1 + inicial2 + nombre).toLowerCase().replace(" ", "");
    }

    private void activarUsername() {
        DocumentListener escucha = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                lblUsername.setText(generarUsername());
            }
            public void removeUpdate(DocumentEvent e) {
                lblUsername.setText(generarUsername());
            }
            public void changedUpdate(DocumentEvent e) {
                lblUsername.setText(generarUsername());
            }
        };
        txtPrimerNombre.getDocument().addDocumentListener(escucha);
        txtPrimerApellido.getDocument().addDocumentListener(escucha);
        txtSegundoApellido.getDocument().addDocumentListener(escucha);
    }
}