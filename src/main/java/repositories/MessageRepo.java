package repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import entities.Message;
import entities.Subscriber;
import mappers.MessageRowMapper;

@Repository
public class MessageRepo {

	@Autowired
	JdbcTemplate jdbc;
	@Autowired
	SubscriberRepo subscriberRepo;
	
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
	
	public Map<Subscriber,Message> getNextUnsendMessage() throws Exception{
		
		try {
		Map<Subscriber,Message> tuple = new HashMap<>();
			
		String sql = "SELECT subscriber_id,message_id FROM public.message_subscriber\n"
				+ "WHERE sent = false LIMIT 1 OFFSET 0; ";
		
		SqlRowSet result = jdbc.queryForRowSet(sql);

		result.first();
				
		    Message message = getMessageById(result.getInt("message_id"));
		    Subscriber subscriber = subscriberRepo.getSubscriberById(result.getInt("subscriber_id"));
		
		 if(message.getContent() != null) {
		    tuple.put(subscriber, message);
		    return tuple;
		 }else {
			 throw new Exception("Nothing to send!");			 
		 }
		}catch(ArrayIndexOutOfBoundsException e) {
			
		}
		return null;
	}
	
	public void markSentMessage (String subscriber_id) {
		 jdbc.update("UPDATE public.message_subscriber\n"
		 		+ "	SET sent = true"
		 		+ " WHERE sent = false AND subscriber_id='"+subscriber_id+"'; ");
	}
}
