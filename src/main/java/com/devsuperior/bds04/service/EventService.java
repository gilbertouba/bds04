package com.devsuperior.bds04.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.entities.Role;
import com.devsuperior.bds04.exceptions.DatabaseException;
import com.devsuperior.bds04.exceptions.ResourceNotFoundException;
import com.devsuperior.bds04.repository.CityRepository;
import com.devsuperior.bds04.repository.EventRepository;


@Service
public class EventService {
	
	@Autowired	
	private EventRepository eventRepository;

	@Autowired	
	private CityRepository cityRepository;
	
	@Transactional(readOnly=true)
	public List<EventDTO> findAll(){		
		List<Event> list =eventRepository.findAll();		
		return list.stream().map(x -> new EventDTO(x)).collect(Collectors.toList());	
	}
	
    @Transactional
	public EventDTO insert(EventDTO dto) {
		
    	Event entity = new Event();
    	copyDtoToEntity(dto,entity);
		entity = eventRepository.save(entity);
		return new EventDTO(entity);
	}
        
	
	public Page<EventDTO> findAllPaged(Pageable pageable) {
		
		Page<Event> list = eventRepository.findAll(pageable);
		
		return list.map(x->new EventDTO(x));
		
	}
	
	private void copyDtoToEntity(EventDTO dto,Event entity) {
			
			entity.setName(dto.getName());
			entity.setDate(dto.getDate());
			entity.setDate(dto.getDate());
			entity.setUrl(dto.getUrl());

			City city = cityRepository.getOne(dto.getCityId());
			entity.setCity(city);
			
		
	};


}
