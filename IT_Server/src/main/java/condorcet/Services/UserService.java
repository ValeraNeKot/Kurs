package condorcet.Services;

import condorcet.DataAccessObjects.UserDAO;
import condorcet.Interfaces.DAO;
import condorcet.Interfaces.Service;
//import condorcet.Models.Entities.Passenger;
import condorcet.Models.Entities.PersonData;
import condorcet.Models.Entities.Specialist;
import condorcet.Models.Entities.User;
//import condorcet.Models.Entities.UserMark;

import java.util.List;

public class UserService implements Service<User> {

    DAO daoService = new UserDAO();

    @Override
    public User findEntity(int id) {
        User entity = (User) daoService.findById(id);   
        return entity;
    }

    @Override
    public void saveEntity(User entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(User entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(User entity) {
        daoService.update(entity);
    }

    @Override
    public List<User> findAllEntities() {

        return daoService.findAll();
    }
}
