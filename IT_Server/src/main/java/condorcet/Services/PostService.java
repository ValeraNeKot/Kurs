package condorcet.Services;

import java.util.List;

import condorcet.DataAccessObjects.PostDAO;
import condorcet.Interfaces.DAO;
import condorcet.Interfaces.Service;
import condorcet.Models.Entities.Post;

public class PostService implements Service<Post>{

	 DAO daoService = new PostDAO();

	    @Override
	    public Post findEntity(int id) {
	    	Post entity = (Post) daoService.findById(id);   
	        return entity;
	    }

	    @Override
	    public void saveEntity(Post entity) {
	        daoService.save(entity);
	    }

	    @Override
	    public void deleteEntity(Post entity) {
	        daoService.delete(entity);
	    }

	    @Override
	    public void updateEntity(Post entity) {
	        daoService.update(entity);
	    }

	    @Override
	    public List<Post> findAllEntities() {

	        return daoService.findAll();
	    }


}
