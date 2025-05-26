package Control;

import Model.Card;
import Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserControl {

    public static void registrarUsuario(User u) {
        String db_url = "jdbc:mysql://localhost:3306/toaiwa";
        String name = "natalia";
        String passw = "natalia1";
        String insertQy = "insert into user (name, surename, email, password) values(?,?,?,?)";

        try {
            Connection con = DriverManager.getConnection(db_url, name, passw);
            PreparedStatement ps = con.prepareStatement(insertQy);
            ps.setString(1, u.getName());
            ps.setString(2, u.getSurename());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword());

            int addrows = ps.executeUpdate();
            if (addrows>0) System.out.println("Usuario registrado exitosamente");

            ps.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
