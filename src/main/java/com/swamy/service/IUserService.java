package com.swamy.service;

import java.util.List;
import java.util.Optional;

import com.swamy.dto.UserDTO;

public interface IUserService {

	public Integer saveUser(UserDTO user);
	public List<UserDTO> getAllUsers();
	public UserDTO getOneUser(Integer id);
	public void updateUser(UserDTO user);
	public void deleteUser(Integer id);
	public Optional<UserDTO> getOneUserById(Integer id);
}

