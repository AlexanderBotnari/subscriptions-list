package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entities.Subscriber;
import repositories.SubscriberRepo;

@RestController
public class SubscriberController {
    
	@Autowired
	SubscriberRepo sr;
	
	@RequestMapping("/subscribers")
	public List<Subscriber> subscribersIndex() {
		List<Subscriber> subscribers = sr.getSubscribers();
		return subscribers;
		
	}
}
