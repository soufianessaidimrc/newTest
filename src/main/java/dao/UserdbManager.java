package dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserdbManager {
    private DataSource dataSource;
    Connection connect;

    public UserdbManager(DataSource theDataSource) {
        dataSource = theDataSource;
        try {
            this.connect = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean login(String email, String password) {

        ResultSet result = null;
        try {
            System.out.println("email : " + email + " password :" + password);
            String sql = "select * from user where email = ? AND password = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            result = statement.executeQuery();
            if (result.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public int getUserIDByEmail(String email) {
        ResultSet res = null;
        try {
            String sql = "select user_id from user where email = ? ";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, email);
            res = statement.executeQuery();
            while (res.next()) {

                return res.getInt("user_id");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;

    }

    public boolean userIsAdmin(String email) {

        int id = getUserIDByEmail(email);
        if (id != -1) {

            try {
                ResultSet res = null;
                String sql = "select user_id from admin where user_id = ? ";
                PreparedStatement statement = connect.prepareStatement(sql);
                statement.setInt(1, id);
                res = statement.executeQuery();
                if (res.next()) {
                    System.out.println("UserIsAdmin");
                    return true;
                } else {
                    return false;
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return false;
    }

}
