package com.bezkoder.spring.datajpa.dto;

import com.bezkoder.spring.datajpa.model.Family;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;


public class MemberDefaultDto {
    private Long memberId;
    Boolean familyHead;
    String firstName;
    String lastName;
    Date dateOfBirth;
    String bloodGroup;
    String mobile;
    String whatsappMobile;
    String area;
    String status;
    String role;
    String dhowanPani;
    String ratriBhojanTyag;
    String fatherName;
    String profession;
    String navkarsi;
    String[] sthanak;
    String[] interest;
    String availability;
    String monthlyHours;
    String garamPani;
    String subArea;
    @Lob
    @Column(name = "photo", columnDefinition = "MEDIUMBLOB")
    private byte[] photo;

    public MemberDefaultDto(Long memberId, Boolean familyHead, String firstName, String lastName,
                            Date dateOfBirth, String bloodGroup, String mobile, String whatsappMobile,
                            String area, String status, String role, String dhowanPani,
                            String fatherName, String profession, String[] sthanak,
                            String[] interest, String garamPani, byte[] photo, String subArea) {
        this.memberId = memberId;
        this.familyHead = familyHead;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bloodGroup = bloodGroup;
        this.mobile = mobile;
        this.whatsappMobile = whatsappMobile;
        this.area = area;
        this.status = status;
        this.role = role;
        this.dhowanPani = dhowanPani;
        this.fatherName = fatherName;
        this.profession = profession;
        this.sthanak = sthanak;
        this.interest = interest;
        this.garamPani = garamPani;
        this.photo = photo;
        this.area = subArea;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Boolean getFamilyHead() {
        return familyHead;
    }

    public void setFamilyHead(Boolean familyHead) {
        this.familyHead = familyHead;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWhatsappMobile() {
        return whatsappMobile;
    }

    public void setWhatsappMobile(String whatsappMobile) {
        this.whatsappMobile = whatsappMobile;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDhowanPani() {
        return dhowanPani;
    }

    public void setDhowanPani(String dhowanPani) {
        this.dhowanPani = dhowanPani;
    }

    public String getRatriBhojanTyag() {
        return ratriBhojanTyag;
    }

    public void setRatriBhojanTyag(String ratriBhojanTyag) {
        this.ratriBhojanTyag = ratriBhojanTyag;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfessiondd(String profession) {
        this.profession = profession;
    }

    public String getNavkarsi() {
        return navkarsi;
    }

    public void setNavkarsi(String navkarsi) {
        this.navkarsi = navkarsi;
    }

    public String[] getSthanak() {
        return sthanak;
    }

    public void setSthanak(String[] sthanak) {
        this.sthanak = sthanak;
    }

    public String[] getInterest() {
        return interest;
    }

    public void setInterest(String[] interest) {
        this.interest = interest;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getMonthlyHours() {
        return monthlyHours;
    }

    public void setMonthlyHours(String monthlyHours) {
        this.monthlyHours = monthlyHours;
    }

    public String getGaramPani() {
        return garamPani;
    }

    public void setGaramPani(String garamPani) {
        this.garamPani = garamPani;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSubArea() {
        return subArea;
    }

    public void setSubArea(String subArea) {
        this.subArea = subArea;
    }

    //    public Family getFamily() {
//        return family;
//    }
//
//    public void setFamily(Family family) {
//        this.family = family;
//    }
}
