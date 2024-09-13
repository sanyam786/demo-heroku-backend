package com.bezkoder.spring.datajpa.repository;
import com.bezkoder.spring.datajpa.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
