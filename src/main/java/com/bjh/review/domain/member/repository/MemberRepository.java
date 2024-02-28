package com.bjh.review.domain.member.repository;

import com.bjh.review.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member,Long>{
    Member findMemberByEmail(String email);
}
