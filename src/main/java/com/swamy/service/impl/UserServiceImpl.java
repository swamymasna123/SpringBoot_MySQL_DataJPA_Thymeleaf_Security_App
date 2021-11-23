package com.swamy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import org.springframework.util.AntPathMatcher;

import com.swamy.dto.UserDTO;
import com.swamy.entity.UserEntity;
import com.swamy.repository.UserRepository;
import com.swamy.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService , UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Integer saveUser(UserDTO user) {

		Integer count = null;
		//We got the Data from UserDTO class Object
		//So we need to copy UserDTO Data to UserEntity class Object
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		String encodedPwd = passwordEncoder.encode(userEntity.getPassword());
		userEntity.setPassword(encodedPwd);
		count = userRepository.save(userEntity).getUserId();
		return count;
	}


	@Override
	public List<UserDTO> getAllUsers() {
		List<UserDTO> listDTO = new ArrayList<>();

		List<UserEntity> userEntityList = userRepository.findAll();
		userEntityList.forEach(entity ->{
			UserDTO user = new UserDTO();
			BeanUtils.copyProperties(entity, user);
			listDTO.add(user);
		});

		return listDTO;
	}

	@Override
	public UserDTO getOneUser(Integer id) {

		UserDTO userDTO = new UserDTO();

		Optional<UserEntity> userEntity = userRepository.findById(id);

		if(!userEntity.isEmpty())
		{
			BeanUtils.copyProperties(userEntity.get(), userDTO);
		}
		return userDTO;
	}

	@Override
	public void updateUser(UserDTO user) {
		//We have the Data from UserDTO class Object
		//So we need to Copy Data from UserDTO Object to UserEntity class Object
		
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(user, entity);
		userRepository.save(entity);
	}

	@Override
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public Optional<UserDTO> getOneUserById(Integer id) {
		Optional<UserDTO>userDTO = null;
		
		Optional<UserEntity> userEntity = userRepository.findById(id);
		if(!userEntity.isEmpty()) {
			userDTO = Optional.of(new UserDTO());
			BeanUtils.copyProperties(userEntity.get(), userDTO.get());
		}
		
		return userDTO;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> opt = userRepository.findByMailId(username);
		if(opt.isEmpty()) {
			throw new UsernameNotFoundException("User Not Exists");
		}
		
		UserEntity user = opt.get();
		return new User(
				username, 
				user.getPassword(), 
				user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toSet())
				);
	}

}


