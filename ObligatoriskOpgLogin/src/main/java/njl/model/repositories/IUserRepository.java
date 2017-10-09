package njl.model.repositories;

import njl.model.entities.User;

import java.util.ArrayList;

public interface IUserRepository {

    // Create
    public void create(User us);

    // Read
    public ArrayList<User> readAll();
    public User read(int id);
    public User login(int id);

    // Update
    public void update(User us);
    public User updatepw(User us);

    // Delete
    public void delete(int id);
}
