package com.devsuperior.bds04.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.RoleDto;
import com.devsuperior.bds04.dto.UserDto;
import com.devsuperior.bds04.entities.Role;
import com.devsuperior.bds04.entities.User;
import com.devsuperior.bds04.exceptions.DatabaseException;
import com.devsuperior.bds04.exceptions.ResourceNotFoundException;
import com.devsuperior.bds04.repository.RoleRepository;
import com.devsuperior.bds04.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {
	
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);
	
	@Autowired	
	private UserRepository userRepository;

	@Autowired	
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional(readOnly=true)
	public List<UserDto> findAll(){		
		List<User> list =userRepository.findAll();		
		return list.stream().map(x -> new UserDto(x)).collect(Collectors.toList());	
	}
	
	@Transactional(readOnly=true)
	public UserDto findById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		User entity = obj.orElseThrow(()->new ResourceNotFoundException("Id não encontrado."));
		return new UserDto(entity);
	}


	public Page<UserDto> findAllPaged(Pageable pageable) {
		
		Page<User> list = userRepository.findAll(pageable);
		
		return list.map(x->new UserDto(x));
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			logger.error("Usuário não encontratdo "+username); 
			throw new UsernameNotFoundException("Email não encontrado.");
		}
		logger.info("Usuário encontratdo "+username);
		return user;
	}
	
	

}
