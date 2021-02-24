package examples.alexander.tasks;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import entities.Message;
import entities.Subscriber;
import repositories.MessageRepo;

@Component
public class EmailScheduler {
	
	@Autowired
	MessageRepo messageRepo;
	@Autowired
	JavaMailSender javaMailSender;

	@Scheduled(fixedRate=60*1000, initialDelay=10000)
	public void sendEmail(){
		System.err.println("Preparing to send email!");

		try {
			Map<Subscriber, Message> tuple = messageRepo.getNextUnsendMessage();
		
		    SimpleMailMessage message = new SimpleMailMessage();
		
		    message.setFrom("alexanderbotnari999gmail.com");
		
		    System.out.println("Email sent to >>> "+iterate(tuple,"email"));
		    message.setTo(iterate(tuple,"email"));
		
		    message.setSubject("Newsletter");
		 
		    System.out.println("Message to "+iterate(tuple,"email")+" >>> "+iterate(tuple,"message"));
		    message.setText(iterate(tuple,"message"));
	        javaMailSender.send(message);
	        
	        messageRepo.markSentMessage(iterate(tuple,"subscriber_id"));
	        
		} catch (Exception e) {
			
		}
	    
	}
	
	public String iterate(Map<Subscriber, Message> tuple , String columnToExtract) {
		Iterator<Entry<Subscriber, Message>> iterator = tuple.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Entry<Subscriber, Message> entry = iterator.next();
	        switch(columnToExtract.toString()) {
	        case "email" : return entry.getKey().getEmail();
	        case "message": return entry.getValue().getContent();
	        case "subscriber_id": return entry.getKey().getId().toString();
	        default: System.err.println("Missing column to extract!"); break;
	        }
	    }
		return null;
	}
	
//	public String iterateMessage(Map<Subscriber, Message> tuple) {
//		Iterator<Entry<Subscriber, Message>> iterator = tuple.entrySet().iterator();
//	    while (iterator.hasNext()) {
//	        Entry<Subscriber, Message> entry = iterator.next();
//	        return entry.getValue().getContent();
//	    }
//		return null;
//	}
//	
//	public Integer iterateSubscriberId(Map<Subscriber, Message> tuple) {
//		Iterator<Entry<Subscriber, Message>> iterator = tuple.entrySet().iterator();
//	    while (iterator.hasNext()) {
//	        Entry<Subscriber, Message> entry = iterator.next();
//	        return entry.getKey().getId();
//	    }
//		return null;
//	}

}
