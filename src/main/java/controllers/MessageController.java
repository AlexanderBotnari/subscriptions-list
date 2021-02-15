package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entities.Message;
import repositories.MessageRepo;

@RestController
public class MessageController {

		@Autowired
		MessageRepo mr;
		
		@GetMapping("/messages")
		public List<Message> messageIndex() {
			List<Message> messages = mr.getMessages();
			return messages;
		}
		
		@GetMapping("/messages/getMessageById/{id}")
		public Message messageById(@PathVariable Integer id) {
		     return mr.getMessageById(id);
		}
		
		@PostMapping("/messages/add")
		public String messageAdd(@RequestParam(required=true) String content) {
			 mr.saveMessage(new Message(content));
			return "Message added!";
		}
				
}
