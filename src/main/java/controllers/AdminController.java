package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entities.Subscriber;
import repositories.SubscriberRepo;

@RestController
public class AdminController {

	@Autowired
	SubscriberRepo sr;
	
	//Functionality of SubscriberController
	@GetMapping("admin/subscribers")
	public List<Subscriber> subscriberIndex() {
		List<Subscriber> subscribers = sr.getSubscribers();
		return subscribers;
	}
	
	@GetMapping("admin/subscribers/add/{email}/{name}")
	public String subscriberAdd(@PathVariable String email, @PathVariable String name) {
		 sr.save(new Subscriber(name,email));
		return "Subscriber added!";
	}
	
	@GetMapping("admin/subscribers/setName/{email}/{newName}")
	public String subscriberSetName(@PathVariable String email, @PathVariable String newName) {
		 sr.update(email,newName);
		return "Subscriber updated!";
	}
	
    //new functionality of AdminController
	@PostMapping("admin/subscribers/remove")
	public String adminSubscriberRemove(
//			@RequestBody String data
			@RequestParam(required=true) int id
			) {
		sr.removeById(id);
		return "Subscriber "+id+" removed";
		}
	
	@PostMapping("/admin/subscribers/send")
	public String adminSubscribersSendMessage(@RequestParam String message) {
		
		return message;
	}
	
	@PostMapping("/admin/subscribers/setemail")
	public String adminSubscriberSetEmail(@RequestParam(required=true) int id ,
			                              @RequestParam String newEmail){
		
		sr.updateById(id, newEmail);
		
		return "Subscriber "+id+" updated";
	}
	
}
