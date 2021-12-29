package com.philip.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.philip.model.Member;
import com.philip.repository.MemberRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class MemberController {
	@Autowired
	MemberRepository service;

	@CrossOrigin
	@GetMapping("/members")
	public Iterable<Member> getAllMembers() {
		
		// find all registered members
		List<Member> gymBros = service.findAll();
		return gymBros;
		}

	
	// For grabbing current user data
	@CrossOrigin
	@GetMapping("/member/{username}")
	public Member getMember(@PathVariable String username) {
		
		Member member = service.findByUsername(username, Member.class);
		
		if(member != null) {
		
			return member;
		}
		
		return null;
	}

	
	
	@CrossOrigin
	@PostMapping("/add/member")
	public ResponseEntity<?> addMember(@RequestBody Member newMember) {
		
		// Check if member already exists
		Optional<Member> memberByEmail = service.findByEmail(newMember.getEmail());
		Member memberByUsername = service.findByUsername(newMember.getUsername(), Member.class);

		// if the email or username has already been taken
		if (memberByEmail.isPresent() || memberByUsername != null) {
			return ResponseEntity.status(400).body("User already exists");
		}

		newMember.setMbrId(-1L);

		Member added = service.save(newMember); // save() does an insert or update (depends on id passed)
		
		return ResponseEntity.status(200).body("Added: " + added);
		
	}
	
	@CrossOrigin
	@PutMapping("/update/member")
	public @ResponseBody String updateMember(@RequestBody Member updateMember) {
		
		// check if member exists,then update them
		
		Optional<Member> found = service.findById(updateMember.getMbrId());
		
		if(found.isPresent()) {
			service.save(updateMember);
			return "Saved: " + updateMember.toString();
		}
		else {
			return "Could not update member, the id = " + updateMember.getMbrId() + " doesn't exist or could not be found";
		}
		
	}
	
	@DeleteMapping("/delete/member/{id}")
	public ResponseEntity<String> deleteMember(@PathVariable Long id) {
		
		Optional<Member> found = service.findById(id);
		
		if(found.isPresent()) {
			
			service.deleteById(id);
			
			return ResponseEntity.status(200).body("Deleted member with id = " + id);	
		}
		else {
			return ResponseEntity.status(400)
					.header("member id", id + "")
					.body("Member with id = " + id + " not found");
		}
			
	}
	

}