package com.kuvar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.IsFriend;

public interface IsFriendRepository extends JpaRepository<IsFriend, Integer> {
	
	@Query("select if from IsFriend if where if.user2.idUser = :idU and if.status = 'pending'")
	public List<IsFriend> getAllRequests(@Param("idU") Integer idUser);
	
	@Query("select if from IsFriend if where if.user2.idUser = :u2 and if.user1.idUser = :u1")
	public IsFriend getIsFriend(@Param("u2") Integer idUser2, @Param("u1") Integer idUser1);

}
