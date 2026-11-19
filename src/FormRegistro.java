import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Image;
import java.io.File;
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
    private String rutaFoto = "";

    public FormRegistro(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
        setTitle("BookUsers · Registro");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 650);
        setLocationRelativeTo(null);
        lblUsername.setText("");
        llenarTipos();
        activarUsername();
        activarCargarFoto();
        activarGuardar();
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

    private void activarCargarFoto() {
        btnCargarFoto.addActionListener(e -> {
            JFileChooser selector = new JFileChooser();
            selector.setFileFilter(new FileNameExtensionFilter("Imágenes (png, jpg)", "png", "jpg", "jpeg"));

            int resultado = selector.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivo = selector.getSelectedFile();
                rutaFoto = archivo.getAbsolutePath();

                ImageIcon icono = new ImageIcon(rutaFoto);
                Image escalada = icono.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                lblFoto.setText("");
                lblFoto.setIcon(new ImageIcon(escalada));
            }
        });
    }

    private boolean validarCampos() {
        String tipo = cmbTipoId.getSelectedItem().toString();
        String numero = txtNumeroId.getText().trim();
        String password = new String(pwdPassword.getPassword());
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (numero.isEmpty()) {
            avisar("El número de identificación es obligatorio.");
            return false;
        }
        boolean esPasaporteOExtranjero = tipo.startsWith("41") || tipo.startsWith("42");
        if (!esPasaporteOExtranjero && !numero.matches("\\d+")) {
            avisar("El número de identificación solo puede tener dígitos.");
            return false;
        }
        if (txtPrimerNombre.getText().trim().isEmpty()) {
            avisar("El primer nombre es obligatorio.");
            return false;
        }
        if (txtPrimerApellido.getText().trim().isEmpty()) {
            avisar("El primer apellido es obligatorio.");
            return false;
        }
        if (password.isEmpty()) {
            avisar("La contraseña es obligatoria.");
            return false;
        }
        if (telefono.isEmpty()) {
            avisar("El teléfono es obligatorio.");
            return false;
        }
        if (correo.isEmpty() || !correo.contains("@")) {
            avisar("El correo es obligatorio y debe contener @.");
            return false;
        }
        return true;
    }

    private void avisar(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Datos incompletos", JOptionPane.WARNING_MESSAGE);
    }

    private void limpiarFormulario() {
        cmbTipoId.setSelectedIndex(0);
        txtNumeroId.setText("");
        txtPrimerNombre.setText("");
        txtSegundoNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        lblUsername.setText("");
        pwdPassword.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        lblFoto.setIcon(null);
        lblFoto.setText("");
        rutaFoto = "";
    }

    private void activarGuardar() {
        btnGuardar.addActionListener(e -> {
            if (!validarCampos()) {
                return;
            }

            Usuario u = new Usuario(
                    Usuario.generarId(),
                    cmbTipoId.getSelectedItem().toString(),
                    txtNumeroId.getText().trim(),
                    txtPrimerNombre.getText().trim(),
                    txtSegundoNombre.getText().trim(),
                    txtPrimerApellido.getText().trim(),
                    txtSegundoApellido.getText().trim(),
                    generarUsername(),
                    new String(pwdPassword.getPassword()),
                    txtTelefono.getText().trim(),
                    txtCorreo.getText().trim(),
                    rutaFoto
            );

            usuarios.add(u);
            System.out.println(u);
            JOptionPane.showMessageDialog(this, "Usuario registrado: " + u.getUsername());
            limpiarFormulario();
        });
    }
}