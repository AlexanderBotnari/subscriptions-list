package repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.Message;
import mappers.MessageRowMapper;

@Repository
public class MessageRepo {

	@Autowired
	JdbcTemplate jdbc;
	
	public List<Message> getMessages() {
		return jdbc.query("select * from messages", new MessageRowMapper());
	}
	
	public Message getMessageById(int id) {
		return jdbc.queryForObject("select * from messages\n"
				+ "WHERE id="+id+";",new MessageRowMapper());
	}
	
	public void saveMessage (Message message) {
		 jdbc.update("INSERT INTO public.messages(\n"
		 		+ " content)\n"
		 		+ "	VALUES ('"+message.getContent()+"');");
	}

}
