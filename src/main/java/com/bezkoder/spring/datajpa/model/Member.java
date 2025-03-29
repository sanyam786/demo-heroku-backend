package com.bezkoder.spring.datajpa.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    Boolean checkedSameAddress;
    Boolean checkedSameWhatsappNumber;
    String currentAddress;
    String profession;
    String professionAddress;
    String professionEmail;
    String professionNumber;
    Boolean checkIAffirm;
    String aborigine;
    String status;
    Date createDateTime;
    Date lastUpdatedDateTime;
    String createdByUser;
    String lastUpdatedByUser;
    String role;
    String dhowanPani;
    String ratriBhojanTyag;
    String professiondd;
    String navkarsi;
    String[] sthanak;
    String[] interest;
    String availability;
    String monthlyHours;
    Boolean sameAddAsFamilyHeadAddCheck;
    String garamPani;
    @Column
    private Double latitude;
    @Column
    private Double longitude;
    @Lob
    @Column(name = "photo", columnDefinition = "MEDIUMBLOB")
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "familyId")
    @JsonBackReference
    private Family family;

    // Getters and setters

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

    public Boolean getCheckedSameAddress() {
        return checkedSameAddress;
    }

    public void setCheckedSameAddress(Boolean checkedSameAddress) {
        this.checkedSameAddress = checkedSameAddress;
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

    public Boolean getCheckIAffirm() {
        return checkIAffirm;
    }

    public void setCheckIAffirm(Boolean checkIAffirm) {
        this.checkIAffirm = checkIAffirm;
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

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Boolean getCheckedSameWhatsappNumber() {
        return checkedSameWhatsappNumber;
    }

    public void setCheckedSameWhatsappNumber(Boolean checkedSameWhatsappNumber) {
        this.checkedSameWhatsappNumber = checkedSameWhatsappNumber;
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

    public String getProfessiondd() {
        return professiondd;
    }

    public void setProfessiondd(String professiondd) {
        this.professiondd = professiondd;
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

    public Boolean getSameAddAsFamilyHeadAddCheck() {
        return sameAddAsFamilyHeadAddCheck;
    }

    public void setSameAddAsFamilyHeadAddCheck(Boolean sameAddAsFamilyHeadAddCheck) {
        this.sameAddAsFamilyHeadAddCheck = sameAddAsFamilyHeadAddCheck;
    }

    public String getGaramPani() {
        return garamPani;
    }

    public void setGaramPani(String garamPani) {
        this.garamPani = garamPani;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}