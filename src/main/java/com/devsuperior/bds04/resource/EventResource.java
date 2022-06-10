package com.devsuperior.bds04.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.service.EventService;



@RestController
@RequestMapping(value ="/events")
public class EventResource {
	
	@Autowired
	private EventService EventService;

	@GetMapping
	public ResponseEntity<Page<EventDTO>> finddAll(Pageable pageable){
		
		Page<EventDTO> list = EventService.findAllPaged(pageable);		
		return ResponseEntity.ok().body(list);				
	}
	
	@PostMapping
	public ResponseEntity<EventDTO> insert(@Valid @RequestBody EventDTO dto){
		
		dto = EventService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{d}")
				  .buildAndExpand(dto.getId()).toUri();		
		return ResponseEntity.created(uri).body(dto);				
	}  

}
