package condorcet.Services;

import java.util.List;

import condorcet.DataAccessObjects.DepartmentDAO;
import condorcet.Interfaces.DAO;
import condorcet.Interfaces.Service;
import condorcet.Models.Entities.Department;

public class DepartmentService implements Service<Department>{

    DAO daoService = new DepartmentDAO();

    @Override
    public Department findEntity(int id) {
    	Department entity = (Department) daoService.findById(id);   
        return entity;
    }

    @Override
    public void saveEntity(Department entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Department entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Department entity) {
        daoService.update(entity);
    }

    @Override
    public List<Department> findAllEntities() {

        return daoService.findAll();
    }


}
