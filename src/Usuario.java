public class Usuario {

    private int id;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String username;
    private String password;
    private String telefono;
    private String correo;
    private String rutaFoto;


    // estos atributos son private entonces solo se pueden usar dentro de la clase, estos son los datos de los usuarios.
    public Usuario(int id, String tipoIdentificacion, String numeroIdentificacion, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String username, String password, String telefono, String correo, String rutaFoto) {
        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.username = username;
        this.password = password;
        this.telefono = telefono;
        this.correo = correo;
        this.rutaFoto = rutaFoto;

        // Mi nota: recibe los datos y los guarda en los atributos;
        // this.id es el atributo y id es el dato que llegó.



    }

    public int getId() {
        return id;
    }

    public String getTipoIdentificacion(){
        return tipoIdentificacion;
    }

    public String getNumeroIdentificacion(){
        return numeroIdentificacion;
    }

    public String getPrimerNombre(){
        return primerNombre;
    }

    public String getSegundoNombre(){
        return segundoNombre;
    }

    public String getPrimerApellido(){
        return primerApellido;
    }

    public String getSegundoApellido(){
        return segundoApellido;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getTelefono(){
        return telefono;
    }

    public String getCorreo(){
        return correo;
    }

    public String getRutaFoto(){
        return rutaFoto;
    }

    // Mi nota: como los atributos son private,
    // los get permiten leerlos desde otras clases; return devuelve el valor.
}



