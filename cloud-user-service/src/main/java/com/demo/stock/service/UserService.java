package com.demo.stock.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.stock.entity.UserEntity;

public interface UserService {

	/**
	 * ��ҳ��ѯ�û��б�
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page<UserEntity> findAll(int page, int pageSize);
	
	/**
	 * ��ѯ�����û��б�
	 * 
	 * @return
	 */
	public List<UserEntity> findAllUsers();
	
	/**
	 * ����ID��ѯһ���û�
	 * 
	 * @param 
	 * @return
	 */
	public UserEntity findUserById(Integer id);
	
	/**
	 * ע��һ���û�
	 * 
	 * @param user
	 * @return
	 */
	public UserEntity registUser(UserEntity user);
	
	/**
	 * �����û���Ϣ
	 * 
	 * @param user
	 * @return
	 */
	public UserEntity updateUser(UserEntity user);
	
	/**
	 * �����û�
	 * 
	 * @param id
	 * @return
	 */
	public UserEntity activeUser(Integer id);
	
	/**
	 * ɾ��һ���û�
	 * 
	 * @param id
	 */
	public void delete(Integer id);
}
