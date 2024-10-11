package br.edu.ifsp.ifitness.model.util.users;

import br.edu.ifsp.ifitness.model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

public class UserDao {

    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        String passwordEncripted = PasswordEncoder.encode(password);

        String sql = "select id,name,email from Users where email=? and password=?";
        Optional<User> optional = Optional.empty();
        try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, passwordEncripted);
            optional = getUser(optional, ps);
            return optional;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a consulta no BD", sqlException);
        }
    }

    public Optional<User> getUserByEmail(String email){
        String sql = "select id,name,email from Users where email=?";
        Optional<User> optional = Optional.empty();
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, email);
            optional = getUser(optional, ps);
        }catch (SQLException e) {
            throw new RuntimeException("Erro durante a consulta no BD", e);
        }
        return optional;
    }

    private Optional<User> getUser(Optional<User> optional, PreparedStatement ps) throws SQLException {
        try(ResultSet rs = ps.executeQuery()) {
            if(rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                optional = Optional.of(user);
            }
        }
        return optional;
    }

    public Boolean save(User user){
        Optional<User> optional = getUserByEmail(user.getEmail());
        if(optional.isPresent()) {
            return false;
        }
        String sql = "insert into Users (name, email, password, "
                + "birth_date, gender, active) values (?,?,?,?,?,?)";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setDate(4, Date.valueOf(user.getDateOfBirth()));
            ps.setString(5, user.getGender().toString());
            ps.setBoolean(6, true);
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException("Erro durante a escrita no BD", e);
        }
        return true;
    }
}