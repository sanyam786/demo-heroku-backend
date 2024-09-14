package com.bezkoder.spring.datajpa.service;
import com.bezkoder.spring.datajpa.dto.FamilyDto;
import com.bezkoder.spring.datajpa.dto.MemberDefaultDto;
import com.bezkoder.spring.datajpa.model.Family;
import com.bezkoder.spring.datajpa.model.Member;
import com.bezkoder.spring.datajpa.model.Tutorial;
import com.bezkoder.spring.datajpa.repository.FamilyRepository;
import com.bezkoder.spring.datajpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.plaf.metal.MetalMenuBarUI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private MemberRepository memberRepository;

    // Create a new family if not exists, and add a new member
    public Family addMemberToFamilyByName(String familyName, Member memberDetails) {
        Optional<Family> familyOpt = familyRepository.findByFamilyName(familyName);

        Family family;
        if (familyOpt.isPresent()) {
            family = familyOpt.get();
        } else {
            family = new Family();
            family.setFamilyName(familyName);
            familyRepository.save(family);
        }

        memberDetails.setFamily(family);
        memberRepository.save(memberDetails);
        return family;
    }

    // Create a new family if not exists, and add a new member
    public Member addMemberToFamily(Long familyId, Member memberDetails) {
        Family family = null;
        if(familyId != null && familyId != 0){
            Optional<Family> familyOpt = familyRepository.findByFamilyId(familyId);
            if (familyOpt.isPresent()) {
                family = familyOpt.get();
            }
        }else {
            family = new Family();
        }

        if(family != null){
            //family.setFamilyName(familyName);
            familyRepository.save(family);

            memberDetails.setFamily(family);
            memberRepository.save(memberDetails);
        }
        return memberDetails;
    }

    public Member updateMemberDetails(Long id, Member member) {
        Optional<Member> memberData = memberRepository.findById(id);
        Member _member = null;
        if (memberData.isPresent()) {
            _member = memberData.get();
            Family family = _member.getFamily();
            _member = member;
            _member.setFamily(family);
            /*_member.setFirstName(member.getFirstName());
            _member.setLastName(member.getLastName());
            _member.setPhoto(member.getPhoto());*/
            _member.setStatus("Pending");
            memberRepository.save(_member);
        }
        return _member;
    }

    public Member updateFamilyHead(Long id) {
        Optional<Member> memberData = memberRepository.findById(id);
        Member _member = null;
        if (memberData.isPresent()) {
            _member = memberData.get();
            Optional<Family> familyData = familyRepository.findByFamilyId(_member.getFamily().getFamilyId());
            if(familyData.isPresent()){
                for (Member member:familyData.get().getMembers()) {
                   if(member.getMemberId() == _member.getMemberId()){
                       member.setFamilyHead(true);
                       memberRepository.save(member);
                   }else {
                       member.setFamilyHead(false);
                       memberRepository.save(member);
                   }
                }
            }
        }
        return _member;
    }

    public Member approveMember(Long id) {
        Optional<Member> memberData = memberRepository.findById(id);
        Member _member = null;
        if (memberData.isPresent()) {
            _member = memberData.get();
            _member.setStatus("Approved");
            memberRepository.save(_member);
        }
        return _member;
    }

    public List<Family> getAllFamilies() {
            List<Family> families = new ArrayList<Family>();
            familyRepository.findAll().forEach(families::add);
            return families;
    }

    public List<MemberDefaultDto> getAllFamiliesForDefaultSearch() {
        List<Object[]> members = memberRepository.getAllFamiliesForDefaultSearch();

        // Map Object[] to MemberDefaultDto
        return members.stream().map(member -> {
            Long memberId = (Long) member[0];
            Boolean familyHead = (Boolean) member[1];
            String firstName = (String) member[2];
            String lastName = (String) member[3];
            Timestamp timestamp = (Timestamp) member[4];
            Date dateOfBirth = (Date) timestamp;
            String bloodGroup = (String) member[5];
            String mobile = (String) member[6];
            String whatsappMobile = (String) member[7];
            String area = (String) member[8];
            String status = (String) member[9];

            return new MemberDefaultDto(memberId, familyHead, firstName, lastName, dateOfBirth, bloodGroup, mobile,
                whatsappMobile, area, status);
        }).collect(Collectors.toList());
        //return members;
    }

    public Family getFamiyById(long id) {
        Family family = null;
        Optional<Family> familyData = familyRepository.findById(id);
        if (familyData.isPresent()) {
            family = familyData.get();
        }
        return family;
    }

    public Family getFamiyByMemberId(long id) {
        Family family = null;
        Optional<Member> memberData = memberRepository.findById(id);
        if (memberData.isPresent()) {
            family = memberData.get().getFamily();
        }
        return family;
    }

    public long getFamiyIdByMemberId(long id) {
        Family family = null;
        Optional<Member> memberData = memberRepository.findById(id);
        if (memberData.isPresent()) {
            Member member = memberData.get();
            family = memberData.get().getFamily();
        }
        return family.getFamilyId();
    }

    public Member getMemberById(long id) {
        Member member = null;
        Optional<Member> memberData = memberRepository.findById(id);
        if (memberData.isPresent()) {
            member = memberData.get();
        }
        return member;
    }
}
