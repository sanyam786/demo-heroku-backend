package com.bezkoder.spring.datajpa.dto;

import java.util.Date;

public class MemberAllSearchDto {

    private Long memberId;
    Boolean familyHead;
    String firstName;
    String lastName;
    String fatherName;
    String gender;
    Date dateOfBirth;
    String maritalStatus;
    String bloodGroup;
    String education;
    String permanentAddress;
    String mobile;
    String altMobile;
    String whatsappMobile;
    String email;
    String area;
    String currentAddress;
    String profession;
    String professionAddress;
    String professionEmail;
    String professionNumber;
    String aborigine;
    String status;
    Date createDateTime;
    Date lastUpdatedDateTime;
    String createdByUser;
    String lastUpdatedByUser;
    String role;

    public MemberAllSearchDto(Long memberId, Boolean familyHead, String firstName, String lastName, String fatherName, String gender, Date dateOfBirth, String maritalStatus, String bloodGroup, String education, String permanentAddress, String mobile, String altMobile, String whatsappMobile, String email, String area, String currentAddress, String profession, String professionAddress, String professionEmail, String professionNumber, String aborigine, String status, Date createDateTime, Date lastUpdatedDateTime, String createdByUser, String lastUpdatedByUser, String role) {
        this.memberId = memberId;
        this.familyHead = familyHead;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.maritalStatus = maritalStatus;
        this.bloodGroup = bloodGroup;
        this.education = education;
        this.permanentAddress = permanentAddress;
        this.mobile = mobile;
        this.altMobile = altMobile;
        this.whatsappMobile = whatsappMobile;
        this.email = email;
        this.area = area;
        this.currentAddress = currentAddress;
        this.profession = profession;
        this.professionAddress = professionAddress;
        this.professionEmail = professionEmail;
        this.professionNumber = professionNumber;
        this.aborigine = aborigine;
        this.status = status;
        this.createDateTime = createDateTime;
        this.lastUpdatedDateTime = lastUpdatedDateTime;
        this.createdByUser = createdByUser;
        this.lastUpdatedByUser = lastUpdatedByUser;
        this.role = role;
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

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAltMobile() {
        return altMobile;
    }

    public void setAltMobile(String altMobile) {
        this.altMobile = altMobile;
    }

    public String getWhatsappMobile() {
        return whatsappMobile;
    }

    public void setWhatsappMobile(String whatsappMobile) {
        this.whatsappMobile = whatsappMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfessionAddress() {
        return professionAddress;
    }

    public void setProfessionAddress(String professionAddress) {
        this.professionAddress = professionAddress;
    }

    public String getProfessionEmail() {
        return professionEmail;
    }

    public void setProfessionEmail(String professionEmail) {
        this.professionEmail = professionEmail;
    }

    public String getProfessionNumber() {
        return professionNumber;
    }

    public void setProfessionNumber(String professionNumber) {
        this.professionNumber = professionNumber;
    }

    public String getAborigine() {
        return aborigine;
    }

    public void setAborigine(String aborigine) {
        this.aborigine = aborigine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(Date lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public String getLastUpdatedByUser() {
        return lastUpdatedByUser;
    }

    public void setLastUpdatedByUser(String lastUpdatedByUser) {
        this.lastUpdatedByUser = lastUpdatedByUser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
