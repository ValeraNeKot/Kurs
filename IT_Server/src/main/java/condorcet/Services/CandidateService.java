package condorcet.Services;

import java.util.List;

import condorcet.DataAccessObjects.CandidateDAO;
import condorcet.Interfaces.DAO;
import condorcet.Interfaces.Service;
import condorcet.Models.Entities.Candidate;

public class CandidateService implements Service<Candidate>{


    DAO daoService = new CandidateDAO();

    @Override
    public Candidate findEntity(int id) {
    	Candidate entity = (Candidate) daoService.findById(id);   
        return entity;
    }

    @Override
    public void saveEntity(Candidate entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Candidate entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Candidate entity) {
        daoService.update(entity);
    }

    @Override
    public List<Candidate> findAllEntities() {

        return daoService.findAll();
    }

}
