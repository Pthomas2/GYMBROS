package com.philip.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.philip.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	List<Member> findAll();

	Member findByUsername(String username, Class<Member> class1);

	Optional<Member> findByEmail(String email);

}