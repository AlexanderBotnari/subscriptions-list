package repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.Subscriber;
import mappers.SubscriberRowMapper;

@Repository
public class SubscriberRepo {

	@Autowired
	JdbcTemplate jdbc;
	
	public List<Subscriber> getSubscribers() {
		return jdbc.query("select * from subscribers", new SubscriberRowMapper());
		
	}
	
	public void save (Subscriber subscriber) {
		 jdbc.update("INSERT INTO public.subscribers(\n"
		 		+ " name, email)\n"
		 		+ "	VALUES ('"+subscriber.getName()+"', '"+subscriber.getEmail()+"');");
	}
	
	public void update (String email, String newName) {
		 jdbc.update("UPDATE public.subscribers\n"
		 		+ "	SET name='"+newName+"'"
		 		+ "	WHERE email='"+email+"';");
	}
	
	public void updateById (int id, String newEmail) {
		 jdbc.update("UPDATE public.subscribers\n"
		 		+ "	SET email='"+newEmail+"'"
		 		+ "	WHERE id="+id+";");
	}
	
	public void removeById (int id) {
		 jdbc.update("DELETE FROM public.subscribers\n"
		 		+ "	WHERE id="+id+";");
	}
}
