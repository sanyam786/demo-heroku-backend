package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Family;
import com.bezkoder.spring.datajpa.model.Member;
import com.bezkoder.spring.datajpa.repository.FamilyRepository;
import com.bezkoder.spring.datajpa.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public ResponseEntity<Member> updateMemberDetails(@PathVariable("id") long id, @RequestBody Member member, @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
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
            /*List<MemberDto> memberDtos = members.stream()
                    .map(MemberDto::new)
                    .collect(Collectors.toList());*/
            //return ResponseEntity.ok(memberDtos);
            return new ResponseEntity<>(members, HttpStatus.OK);
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
}
