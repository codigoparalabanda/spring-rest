package com.slash.videotutorial.spring.rest.web;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slash.videotutorial.spring.rest.domain.Liga;
import com.slash.videotutorial.spring.rest.service.LigaService;

@RestController
@RequestMapping("/liga")
@CrossOrigin("*")
public class LigaController {

	@Autowired
	private LigaService ligaService;
	
	@GetMapping
	public ResponseEntity<Set<Liga>> findAll() {
		return new ResponseEntity<>(ligaService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Liga> findById(@PathVariable("id") Short id) {
		Optional<Liga> liga = ligaService.findById(id);
		
		if(liga.isPresent()) {
			return new ResponseEntity<>(liga.get(), HttpStatus.OK); 
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Liga> post(@RequestBody Liga liga) {
		return new ResponseEntity<>(ligaService.add(liga), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Liga> put(@RequestBody Liga liga) {
		return new ResponseEntity<>(ligaService.update(liga), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> put(@PathVariable("id") Short id) {
		ligaService.removeById(id);
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
