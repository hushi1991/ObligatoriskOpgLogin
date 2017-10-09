package njl.model.repositories;

import njl.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public class UserRepository implements IUserRepository {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public void create(User us){
        jdbc.update("INSERT INTO userlogin(name, address, email, password) VALUES('" + us.getName() +"', '"+ us.getAddress() +"', '"+ us.getEmail() +"', '"+ us.getPassword() +"')");
    }

    @Override
    public ArrayList<User> readAll(){
        SqlRowSet sqlRowSet = jdbc.queryForRowSet("SELECT * FROM userlogin");
        ArrayList<User> users = new ArrayList<>();

        while (sqlRowSet.next()) {
            users.add(new User(sqlRowSet.getInt("id"), sqlRowSet.getString("name"), sqlRowSet.getString("address"), sqlRowSet.getString("email"), sqlRowSet.getString("password"), sqlRowSet.getString("password1"), sqlRowSet.getString("password2")));
        }
        return users;
    }

    @Override
    public User read(int id) {
        SqlRowSet sqlRowSet = jdbc.queryForRowSet("SELECT * FROM userlogin WHERE id =" + id + "");

        if (sqlRowSet.next()){
            return new User(sqlRowSet.getInt("id"), sqlRowSet.getString("name"), sqlRowSet.getString("address"), sqlRowSet.getString("email"), sqlRowSet.getString("password"), sqlRowSet.getString("password1"), sqlRowSet.getString("password2"));
        }
        return new User();
    }

    @Override
    public User login(int id) {
        SqlRowSet sqlRowSet = jdbc.queryForRowSet("SELECT * FROM userlogin WHERE id =" + id + "");

        if (sqlRowSet.next()){
            return new User(sqlRowSet.getInt("id"), sqlRowSet.getString("name"), sqlRowSet.getString("address"), sqlRowSet.getString("email"), sqlRowSet.getString("password"), sqlRowSet.getString("password1"), sqlRowSet.getString("password2"));
        }
        return new User();
    }

    @Override
    public void update(User us){

        jdbc.update("UPDATE userlogin set name = '"+ us.getName() +"', address = '"+ us.getAddress() +"', email = '"+ us.getEmail() +"', password = '"+ us.getPassword() +"' WHERE id =" +us.getUser_id() +"");
    }

    @Override
    public User updatepw(User us){

        SqlRowSet sqlRowSet = jdbc.queryForRowSet("SELECT * FROM userlogin WHERE name = '" +  us.getName()  + "'  AND password = '" +   us.getPassword() + "' ");

        if(sqlRowSet.next()) {
            return new User(sqlRowSet.getInt("id"), sqlRowSet.getString("name"), sqlRowSet.getString("address"), sqlRowSet.getString("email"), sqlRowSet.getString("password"), sqlRowSet.getString("password1"), sqlRowSet.getString("password2"));
        }

        return null;
    }

    @Override
    public void delete(int id) {

        jdbc.update("DELETE FROM userlogin WHERE id=" + id + "");
    }
}
