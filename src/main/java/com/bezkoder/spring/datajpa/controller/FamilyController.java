package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.dto.MemberAllSearchDto;
import com.bezkoder.spring.datajpa.dto.MemberDefaultDto;
import com.bezkoder.spring.datajpa.model.Family;
import com.bezkoder.spring.datajpa.model.Member;
import com.bezkoder.spring.datajpa.repository.FamilyRepository;
import com.bezkoder.spring.datajpa.service.FamilyService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "https://demo-heroku-ui-a3206905a154.herokuapp.com")
@RestController
@RequestMapping("/api/family")
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @Autowired
    FamilyRepository familyRepository;

    @PostMapping("/{familyId}/add-member")
    public ResponseEntity<Member> addMemberToFamily(@PathVariable("familyId") Long familyId, @RequestBody Member memberDetails) {
        Date dateNow = new Date();
        memberDetails.setCreateDateTime(dateNow);
        //memberDetails.setPhoto(memberDetails.getPhoto());
        Member member = familyService.addMemberToFamily(familyId, memberDetails);
        return ResponseEntity.ok(member);
    }

    @PutMapping("/updateMember/{id}")
    public ResponseEntity<Member> updateMemberDetails(@PathVariable("id") long familyId, @RequestBody Member member, @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        Date dateNow = new Date();
        member.setLastUpdatedDateTime(dateNow);
        if(file != null && !file.isEmpty()) {
            member.setPhoto(file.getBytes());
        }
        Member _member = familyService.updateMemberDetails(familyId, member);
        if (_member != null) {
            return new ResponseEntity<>(_member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*@PostMapping("/{familyId}/add-member")
    public ResponseEntity<Member> addMemberToFamily(@PathVariable("familyId") Long familyId, @RequestPart("familyMember") Member memberDetails, @RequestPart("file") MultipartFile file) throws IOException {
        Date dateNow = new Date();
        memberDetails.setCreateDateTime(dateNow);
        memberDetails.setPhoto(file.getBytes());
        Member member = familyService.addMemberToFamily(familyId, memberDetails);
        return ResponseEntity.ok(member);
    }

    @PutMapping("/updateMember/{id}")
    public ResponseEntity<Member> updateMemberDetails(@PathVariable("id") long id, @RequestPart("familyMember") Member member, @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        Date dateNow = new Date();
        member.setLastUpdatedDateTime(dateNow);
        if(file != null && !file.isEmpty()) {
            member.setPhoto(file.getBytes());
        }
        Member _member = familyService.updateMemberDetails(id, member);
        if (_member != null) {
            return new ResponseEntity<>(_member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @PutMapping("/updateFamilyHead/{id}")
    public ResponseEntity<Member> updateFamilyHead(@PathVariable("id") long id) {
        Member _member = familyService.updateFamilyHead(id);
        if (_member != null) {
            return new ResponseEntity<>(_member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateRole/{id}/{role}")
    public ResponseEntity<Member> updateRole(@PathVariable("id") long id, @PathVariable("role") String role) {
        Member _member = familyService.updateRole(id, role);
        if (_member != null) {
            return new ResponseEntity<>(_member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/approveStatus/{id}")
    public ResponseEntity<Member> approveStatus(@PathVariable("id") long id) {
        Member _member = familyService.approveMember(id);
        if (_member != null) {
            return new ResponseEntity<>(_member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/families")
    public ResponseEntity<List<Member>> getAllFamilies() {
        try {
            List<Family> families = familyService.getAllFamilies();
            List<Member> members = new ArrayList<Member>();
            if (families.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            for(int i=0; i < families.size(); i++){
                members.addAll(families.get(i).getMembers());
            }
            return new ResponseEntity<>(members, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/defaultfamilies")
    public ResponseEntity<List<MemberDefaultDto>> getAllFamiliesForDefaultSearch() {
        try {
            List<MemberDefaultDto> memberDefaultDtos = familyService.getAllFamiliesForDefaultSearch();
            //List<Member> members = new ArrayList<Member>();
            if (memberDefaultDtos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
//            for(int i=0; i < memberDefaultDtos.size(); i++){
//                members.addAll(families.get(i).getMembers());
//            }
            return new ResponseEntity<>(memberDefaultDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/families/{id}")
    public ResponseEntity<Family> getFamilyById(@PathVariable("id") long id) {
        Family family = familyService.getFamiyById(id);
        if (family != null) {
            return new ResponseEntity<>(family, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/memberfamilyId/{id}")
    public ResponseEntity<Long> getFamilyIdByMemberId(@PathVariable("id") long id) {
        Long familyId = familyService.getFamiyIdByMemberId(id);
        if (familyId != null) {
            return new ResponseEntity<>(familyId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/memberfamily/{id}")
    public ResponseEntity<Family> getFamilyByMemberId(@PathVariable("id") long id) {
        Family family = familyService.getFamiyByMemberId(id);
        if (family != null) {
            return new ResponseEntity<>(family, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable("id") long id) {
        Member member = familyService.getMemberById(id);
        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/searchAllFilter")
    public List<MemberAllSearchDto> searchMembers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String fatherName,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Date dateOfBirth,
            @RequestParam(required = false) String maritalStatus,
            @RequestParam(required = false) String bloodGroup,
            @RequestParam(required = false) String education,
            @RequestParam(required = false) String permanentAddress,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String altMobile,
            @RequestParam(required = false) String whatsappMobile,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String currentAddress,
            @RequestParam(required = false) String profession,
            @RequestParam(required = false) String professionAddress,
            @RequestParam(required = false) String professionEmail,
            @RequestParam(required = false) String professionNumber,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Boolean familyHead) {

        // Get all members (or this could be a paginated list for performance)
        List<MemberAllSearchDto> allMembers = familyService.searchMembers(firstName, lastName, fatherName, gender,dateOfBirth, maritalStatus, bloodGroup,
                education, permanentAddress, mobile, altMobile, whatsappMobile, email, area, currentAddress, profession, professionAddress,
                professionEmail, professionNumber, status, familyHead);
        return allMembers;
        // Filter the list based on non-null search parameters
        /*return allMembers.stream()
                .filter(member -> (firstName == null || member.getFirstName().toLowerCase().contains(firstName.toLowerCase())))
                .filter(member -> (lastName == null || member.getLastName().toLowerCase().contains(lastName.toLowerCase())))
                .filter(member -> (fatherName == null || member.getFatherName().toLowerCase().contains(fatherName.toLowerCase())))
                .filter(member -> (gender == null || member.getGender().equalsIgnoreCase(gender)))
                .filter(member -> (dateOfBirth == null || member.getDateOfBirth().equals(dateOfBirth)))
                .filter(member -> (maritalStatus == null || member.getMaritalStatus().equalsIgnoreCase(maritalStatus)))
                .filter(member -> (bloodGroup == null || member.getBloodGroup().equalsIgnoreCase(bloodGroup)))
                .filter(member -> (education == null || member.getEducation().toLowerCase().contains(education.toLowerCase())))
                .filter(member -> (permanentAddress == null || member.getPermanentAddress().toLowerCase().contains(permanentAddress.toLowerCase())))
                .filter(member -> (mobile == null || member.getMobile().contains(mobile)))
                .filter(member -> (altMobile == null || member.getAltMobile().contains(altMobile)))
                .filter(member -> (whatsappMobile == null || member.getWhatsappMobile().contains(whatsappMobile)))
                .filter(member -> (email == null || member.getEmail().toLowerCase().contains(email.toLowerCase())))
                .filter(member -> (area == null || member.getArea().toLowerCase().contains(area.toLowerCase())))
                .filter(member -> (currentAddress == null || member.getCurrentAddress().toLowerCase().contains(currentAddress.toLowerCase())))
                .filter(member -> (profession == null || member.getProfession().toLowerCase().contains(profession.toLowerCase())))
                .filter(member -> (professionAddress == null || member.getProfessionAddress().toLowerCase().contains(professionAddress.toLowerCase())))
                .filter(member -> (professionEmail == null || member.getProfessionEmail().toLowerCase().contains(professionEmail.toLowerCase())))
                .filter(member -> (professionNumber == null || member.getProfessionNumber().contains(professionNumber)))
                .filter(member -> (status == null || member.getStatus().equalsIgnoreCase(status)))
                .map(member -> new MemberDto(member))  // Convert to DTO
                .collect(Collectors.toList());*/
    }

    // API to get AI analysis for a member
    @GetMapping("/{memberId}/analyze")
    public String analyzeMember(@PathVariable Long memberId) throws JSONException {
        return familyService.getMemberAnalysis(memberId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteMember(@PathVariable Long id) {
        String deleteMessage = familyService.deleteMemberById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", deleteMessage);
        return ResponseEntity.ok(response); // Returns a JSON object
    }
}
