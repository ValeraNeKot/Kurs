package condorcet.Services;

import java.util.List;

import condorcet.DataAccessObjects.ScheduleDAO;
import condorcet.Interfaces.DAO;
import condorcet.Interfaces.Service;
import condorcet.Models.Entities.Schedule;

public class ScheduleService implements Service<Schedule>{

	 DAO daoService = new ScheduleDAO();

	    @Override
	    public Schedule findEntity(int id) {
	    	Schedule entity = (Schedule) daoService.findById(id);   
	        return entity;
	    }

	    @Override
	    public void saveEntity(Schedule entity) {
	        daoService.save(entity);
	    }

	    @Override
	    public void deleteEntity(Schedule entity) {
	        daoService.delete(entity);
	    }

	    @Override
	    public void updateEntity(Schedule entity) {
	        daoService.update(entity);
	    }

	    @Override
	    public List<Schedule> findAllEntities() {

	        return daoService.findAll();
	    }


}
