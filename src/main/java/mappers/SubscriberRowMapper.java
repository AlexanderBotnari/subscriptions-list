package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import entities.Subscriber;


public class SubscriberRowMapper implements RowMapper<Subscriber>{
    @Override
    public Subscriber mapRow(ResultSet rs, int rowNum) throws SQLException 
    {
    	Subscriber subscriber = new Subscriber();
    	subscriber.setId(rs.getInt("id"));
    	subscriber.setName(rs.getString("name"));
    	subscriber.setEmail(rs.getString("email"));
 
        return subscriber;
    }

}
