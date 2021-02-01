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
}
