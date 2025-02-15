package com.bezkoder.spring.datajpa.repository;
import com.bezkoder.spring.datajpa.dto.MemberAllSearchDto;
import com.bezkoder.spring.datajpa.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "SELECT m.member_id, m.family_head, m.first_name, m.last_name, m.date_of_birth, m.blood_group, m.mobile, m.whatsapp_mobile, m.area, m.status, m.role, m.father_name, m.dhowan_pani, m.profession, m.sthanak, m.interest, m.garam_pani, m.photo " +
            "FROM member m " +
            "JOIN family f ON f.family_id = m.family_id ", nativeQuery = true)
    List<Object[]> getAllFamiliesForDefaultSearch();

    List<Member> findByLastName(String lastName);

    List<Member> findByMobile(String phoneNumber);

    @Query("SELECT new com.bezkoder.spring.datajpa.dto.MemberAllSearchDto(m.memberId, m.familyHead, m.firstName, m.lastName, m.fatherName, m.gender, m.dateOfBirth, m.maritalStatus, m.bloodGroup, m.education," +
            "m.permanentAddress, m.mobile, m.altMobile, m.whatsappMobile, m.email, m.area, m.currentAddress," +
            "m.profession, m.professionAddress, m.professionEmail, m.professionNumber, m.aborigine, m.status," +
            "m.createDateTime, m.lastUpdatedDateTime, m.createdByUser, m.lastUpdatedByUser, m.role) FROM Member m WHERE " +
            "(:firstName IS NULL OR LOWER(m.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND " +
            "(:lastName IS NULL OR LOWER(m.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
            "(:fatherName IS NULL OR LOWER(m.fatherName) LIKE LOWER(CONCAT('%', :fatherName, '%'))) AND " +
            "(:gender IS NULL OR LOWER(m.gender) LIKE LOWER(:gender)) AND " +
            "(:dateOfBirth IS NULL OR m.dateOfBirth = :dateOfBirth) AND " +
            "(:maritalStatus IS NULL OR LOWER(m.maritalStatus) LIKE LOWER(:maritalStatus)) AND " +
            "(:bloodGroup IS NULL OR LOWER(m.bloodGroup) LIKE LOWER(:bloodGroup)) AND " +
            "(:education IS NULL OR LOWER(m.education) LIKE LOWER(CONCAT('%', :education, '%'))) AND " +
            "(:permanentAddress IS NULL OR LOWER(m.permanentAddress) LIKE LOWER(CONCAT('%', :permanentAddress, '%'))) AND " +
            "(:mobile IS NULL OR m.mobile LIKE CONCAT('%', :mobile, '%')) AND " +
            "(:altMobile IS NULL OR m.altMobile LIKE CONCAT('%', :altMobile, '%')) AND " +
            "(:whatsappMobile IS NULL OR m.whatsappMobile LIKE CONCAT('%', :whatsappMobile, '%')) AND " +
            "(:email IS NULL OR LOWER(m.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
            "(:area IS NULL OR LOWER(m.area) LIKE LOWER(CONCAT('%', :area, '%'))) AND " +
            "(:currentAddress IS NULL OR LOWER(m.currentAddress) LIKE LOWER(CONCAT('%', :currentAddress, '%'))) AND " +
            "(:profession IS NULL OR LOWER(m.profession) LIKE LOWER(CONCAT('%', :profession, '%'))) AND " +
            "(:professionAddress IS NULL OR LOWER(m.professionAddress) LIKE LOWER(CONCAT('%', :professionAddress, '%'))) AND " +
            "(:professionEmail IS NULL OR LOWER(m.professionEmail) LIKE LOWER(CONCAT('%', :professionEmail, '%'))) AND " +
            "(:professionNumber IS NULL OR m.professionNumber LIKE CONCAT('%', :professionNumber, '%')) AND " +
            "(:status IS NULL OR LOWER(m.status) LIKE LOWER(:status)) AND " +
            "(:familyHead IS NULL OR m.familyHead = :familyHead)")
    List<MemberAllSearchDto> searchMembers(
            String firstName,
            String lastName,
            String fatherName,
            String gender,
            Date dateOfBirth,
            String maritalStatus,
            String bloodGroup,
            String education,
            String permanentAddress,
            String mobile,
            String altMobile,
            String whatsappMobile,
            String email,
            String area,
            String currentAddress,
            String profession,
            String professionAddress,
            String professionEmail,
            String professionNumber,
            String status,
            Boolean familyHead);
}
