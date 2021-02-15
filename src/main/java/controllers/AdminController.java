package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entities.Subscriber;
import repositories.SubscriberRepo;

@Controller
public class AdminController {

	@Autowired
	SubscriberRepo sr;
	
	@GetMapping("/admin/subscribers")
	public String adminSubscriberIndex(Model model) {
		model.addAttribute("subscribers", sr.getSubscribers());
		return "admin/subscribers";
	}
	
	@GetMapping("/admin/subscribers/compose")
	public String adminSubscriberCompose(@RequestParam() List<Integer> subscribers) {
		
		List<String> emails = sr.getSubscribersEmailsByIds(subscribers);
		System.out.println(emails);
		return "admin/compose";
	}
	
	//Functionality of SubscriberController
//	@GetMapping("admin/subscribers")
//	public List<Subscriber> adminsubscriberIndex() {
//		List<Subscriber> subscribers = sr.getSubscribers();
//		return subscribers;
//	}
	
	@GetMapping("admin/subscribers/add/{email}/{name}")
	public String adminsubscriberAdd(@PathVariable String email, @PathVariable String name) {
		 sr.save(new Subscriber(name,email));
		return "Subscriber added!";
	}
	
	@GetMapping("admin/subscribers/setName/{email}/{newName}")
	public String adminsubscriberSetName(@PathVariable String email, @PathVariable String newName) {
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
