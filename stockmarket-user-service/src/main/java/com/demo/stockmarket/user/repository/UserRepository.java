package com.demo.stockmarket.user.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.stockmarket.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);

	@Modifying
	@Transactional
	@Query(value = "update t_user u set u.active='1' where u.id=?", nativeQuery = true)
	public int activate(@Param("id") Integer id);
}