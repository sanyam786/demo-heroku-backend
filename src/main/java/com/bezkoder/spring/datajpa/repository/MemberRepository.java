package com.bezkoder.spring.datajpa.repository;
import com.bezkoder.spring.datajpa.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "SELECT m.member_id, m.family_head, m.first_name, m.last_name, m.date_of_birth, m.blood_group, m.mobile, m.whatsapp_mobile, m.area, m.status " +
            "FROM member m " +
            "JOIN family f ON f.family_id = m.family_id ", nativeQuery = true)
    List<Object[]> getAllFamiliesForDefaultSearch();
}
