package com.kuvar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Message;
import model.User;

public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	
	@Query("select distinct u from User u where u in (select m.user1 from Message m where m.user2.idUser = :idU) or u in(select m.user2 from Message m where m.user1.idUser = :idU)")
	public List<User> getChatUsers(@Param("idU") Integer idUser);
	
	@Query("select m from Message m where (m.user1.idUser = :idU1 and m.user2.idUser = :idU2) or (m.user1.idUser = :idU2 and m.user2.idUser = :idU1) order by m.date desc")
	public List<Message> getMessagesWith(@Param("idU1") Integer idUser1, @Param("idU2") Integer idUser2);

}
