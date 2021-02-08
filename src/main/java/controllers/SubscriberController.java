package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import entities.Subscriber;
import repositories.SubscriberRepo;

@RestController
public class SubscriberController {
    
	@Autowired
	SubscriberRepo sr;
	
	@GetMapping("/subscribers")
	public List<Subscriber> subscriberIndex() {
		List<Subscriber> subscribers = sr.getSubscribers();
		return subscribers;
		
	}
	
	@GetMapping("/subscribers/add/{email}/{name}")
	public String subscriberAdd(@PathVariable String email, @PathVariable String name) {
		 sr.save(new Subscriber(name,email));
		return "Subscriber added!";
	}
	
	@GetMapping("/subscribers/setName/{email}/{newName}")
	public String subscriberSetName(@PathVariable String email, @PathVariable String newName) {
		 sr.update(email,newName);
		return "Subscriber updated!";
	}
	
}
