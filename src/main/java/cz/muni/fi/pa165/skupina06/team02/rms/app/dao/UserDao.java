package cz.muni.fi.pa165.skupina06.team02.rms.app.dao;

import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;

import java.util.List;

public interface UserDao {
    public User findById(Long id);

    public User findByEmail(String email);

    public List<User> findAll();

    public void create(User user);

    public void delete(User user);

    public void update(User user);
}
