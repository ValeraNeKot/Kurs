package condorcet.Services;

import java.util.List;

import condorcet.DataAccessObjects.UserDAO;
import condorcet.Interfaces.DAO;
import condorcet.Interfaces.Service;
import condorcet.Models.Entities.Specialist;
import condorcet.Models.Entities.User;
import condorcet.DataAccessObjects.SpecialistDAO;

public class SpecialistService implements Service<Specialist>{

    DAO daoService = new SpecialistDAO();

    @Override
    public Specialist findEntity(int id) {
    	Specialist entity = (Specialist) daoService.findById(id);   
        return entity;
    }

    @Override
    public void saveEntity(Specialist entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Specialist entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Specialist entity) {
        daoService.update(entity);
    }

    @Override
    public List<Specialist> findAllEntities() {

        return daoService.findAll();
    }

}
