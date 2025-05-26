package Model;
import Control.UserControl;

import javax.swing.JOptionPane;
import java.time.LocalDate;

public class User {
    private int id;
    private String name;
    private String surename;
    private String email;
    private String password;
    private LocalDate timed;

    public User(String name, String surename, String email, String password) {
        this.name = name;
        this.surename = surename;
        this.email = email;
        this.password = password;
    }

    public static User pedirDatosUsuario() {
        String nombre = JOptionPane.showInputDialog(null, "Introduce tu nombre:");

        String apellido = JOptionPane.showInputDialog(null, "Introduce tu apellido:");

        String email = JOptionPane.showInputDialog(null, "Introduce tu email:");

        String password = JOptionPane.showInputDialog(null, "Introduce tu contraseña:");

        String resumen = "Resumen de tu información:\n"
                + "Nombre: " + nombre + "\n"
                + "Apellido: " + apellido + "\n"
                + "Email: " + email + "\n"
                + "Contraseña: " + password;

        JOptionPane.showMessageDialog(null, resumen, "Resumen de tu información", JOptionPane.INFORMATION_MESSAGE);

        User u = new User(nombre, apellido, email, password);

        UserControl.registrarUsuario(u);
        return u;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getTimed() {
        return timed;
    }

    public void setTimed(LocalDate timed) {
        this.timed = timed;
    }
}
