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
		
		    System.out.println("Email sent to >>> "+iterateSubscriberEmail(tuple));
		    message.setTo(iterateSubscriberEmail(tuple));
		
		    message.setSubject("Newsletter");
		 
		    System.out.println("Message to "+iterateSubscriberEmail(tuple)+" >>> "+iterateMessage(tuple));
		    message.setText(iterateMessage(tuple));
	        javaMailSender.send(message);
	        
	        messageRepo.markSentMessage(iterateSubscriberId(tuple));
	        
		} catch (Exception e) {
			
		}
	    
	}
	
	public String iterateSubscriberEmail(Map<Subscriber, Message> tuple) {
		Iterator<Entry<Subscriber, Message>> iterator = tuple.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Entry<Subscriber, Message> entry = iterator.next();
	        return entry.getKey().getEmail();
	    }
		return null;
	}
	
	public String iterateMessage(Map<Subscriber, Message> tuple) {
		Iterator<Entry<Subscriber, Message>> iterator = tuple.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Entry<Subscriber, Message> entry = iterator.next();
	        return entry.getValue().getContent();
	    }
		return null;
	}
	
	public Integer iterateSubscriberId(Map<Subscriber, Message> tuple) {
		Iterator<Entry<Subscriber, Message>> iterator = tuple.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Entry<Subscriber, Message> entry = iterator.next();
	        return entry.getKey().getId();
	    }
		return null;
	}

}
