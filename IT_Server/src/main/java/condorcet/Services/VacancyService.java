package condorcet.Services;

import java.util.List;

import condorcet.DataAccessObjects.VacancyDAO;
import condorcet.Interfaces.DAO;
import condorcet.Interfaces.Service;
import condorcet.Models.Entities.Vacancy;

public class VacancyService implements Service<Vacancy>{

	DAO daoService = new VacancyDAO();

    @Override
    public Vacancy findEntity(int id) {
    	Vacancy entity = (Vacancy) daoService.findById(id);   
        return entity;
    }

    @Override
    public void saveEntity(Vacancy entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Vacancy entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Vacancy entity) {
        daoService.update(entity);
    }

    @Override
    public List<Vacancy> findAllEntities() {

        return daoService.findAll();
    }

}
