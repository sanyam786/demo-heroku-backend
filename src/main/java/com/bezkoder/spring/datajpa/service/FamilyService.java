package com.bezkoder.spring.datajpa.service;
import com.bezkoder.spring.datajpa.dto.FamilyDto;
import com.bezkoder.spring.datajpa.dto.MemberAllSearchDto;
import com.bezkoder.spring.datajpa.dto.MemberDefaultDto;
import com.bezkoder.spring.datajpa.model.Family;
import com.bezkoder.spring.datajpa.model.Member;
import com.bezkoder.spring.datajpa.model.Tutorial;
import com.bezkoder.spring.datajpa.repository.FamilyRepository;
import com.bezkoder.spring.datajpa.repository.MemberRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.plaf.metal.MetalMenuBarUI;
import java.nio.charset.StandardCharsets;
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

    @Autowired
    private HuggingFaceService huggingFaceService;

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
            if(memberDetails.getSameAddAsFamilyHeadAddCheck()){
                for (Member member: family.getMembers()){
                    if(member.getFamilyHead()){
                        memberDetails.setCurrentAddress(member.getCurrentAddress());
                        memberDetails.setPermanentAddress(member.getPermanentAddress());
                        break;
                    }
                }
            }
            familyRepository.save(family);

            memberDetails.setFamily(family);
            memberRepository.save(memberDetails);

            // Format the unique ID only if it's not already set
            if (memberDetails.getUniqueMemberId() == null) {
                String formattedId = String.format("SJS%07d", memberDetails.getMemberId());
                memberDetails.setUniqueMemberId(formattedId);
                memberDetails = memberRepository.save(memberDetails); // Save again to update the uniqueMemberId
            }
        }
        return memberDetails;
    }

    @Transactional
    public String deleteMemberById(Long memberId) {
        Member member = null;
        if(memberId != null && memberId != 0){
            Optional<Member> memberOpt = memberRepository.findById(memberId);
            if (memberOpt.isPresent()) {
                member = memberOpt.get();
            }
            if(member !=null)
            {
                if(!member.getFamilyHead()){
                    memberRepository.deleteById(memberId);
                    memberRepository.flush(); // Ensure immediate commit
                    return "Member deleted successfully.";
                } else {
                    Family family = null;
                    if(member.getFamily().getFamilyId() != null && member.getFamily().getFamilyId() != 0){
                        Optional<Family> familyOpt = familyRepository.findByFamilyId(member.getFamily().getFamilyId());
                        if (familyOpt.isPresent()) {
                            family = familyOpt.get();
                            if(family.getMembers().size() > 1){
                                return "Member can't be deleted as it has other family members associated";
                            } else {
                                memberRepository.deleteById(memberId);
                                familyRepository.deleteById(member.getFamily().getFamilyId());
                                memberRepository.flush(); // Ensure immediate commit
                                return "Member deleted successfully.";
                            }
                        }
                    }
                }
            }else {
                return "Member Not Found";
            }
        }
        return "Member Not Found";
    }

    public Member updateMemberDetails(Long familyId, Member member) {
        Optional<Member> memberData = memberRepository.findById(member.getMemberId());
        Member _member = null;
        if (memberData.isPresent()) {
            _member = memberData.get();
            Family family = _member.getFamily();
            if(_member.getFamilyHead()){
                for (Member familyMemeber: family.getMembers()){
                    if(!familyMemeber.getFamilyHead() &&
                            familyMemeber.getSameAddAsFamilyHeadAddCheck() != null && familyMemeber.getSameAddAsFamilyHeadAddCheck()){
                        familyMemeber.setCurrentAddress(member.getCurrentAddress());
                        familyMemeber.setPermanentAddress(member.getPermanentAddress());
                        memberRepository.save(familyMemeber);
                    }
                }
            }else if(member.getSameAddAsFamilyHeadAddCheck() != null && member.getSameAddAsFamilyHeadAddCheck()){
                for (Member familyHead: family.getMembers()){
                    if(familyHead.getFamilyHead()){
                        member.setCurrentAddress(familyHead.getCurrentAddress());
                        member.setPermanentAddress(familyHead.getPermanentAddress());
                        break;
                    }
                }
            }
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

    public Member updateRole(Long id, String role) {
        Optional<Member> memberData = memberRepository.findById(id);
        Member _member = null;
        if (memberData.isPresent()) {
            _member = memberData.get();
            _member.setRole(role);
            memberRepository.save(_member);
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
            String role = (String) member[10];
            String fatherName = member.length > 11 ? (String) member[11]: "";
            String dhowanPani = member.length > 12 ? (String) member[12] : "";
            String profession = member.length > 13 ? (String) member[13]: "";
            String sthanakObject = member.length > 14 && member[14] instanceof byte[]
                    ? new String((byte[]) member[14], StandardCharsets.UTF_8)
                    : "";
            String[] sthanak = sthanakObject.isEmpty() ? new String[0] : sthanakObject.split(",");

            String interestObject = member.length > 15 && member[15] instanceof byte[]
                    ? new String((byte[]) member[15], StandardCharsets.UTF_8)
                    : "";
            String[] interest = interestObject.isEmpty() ? new String[0] : interestObject.split(",");

            String garamPani = member.length > 16 ? (String) member[16]: "";
            byte[] photo = member.length > 17 && member[17] instanceof byte[]
                    ? (byte[]) member[17]
                    : new byte[0];
            String subArea = member.length > 18 ? (String) member[18]: "";

            return new MemberDefaultDto(memberId, familyHead, firstName, lastName, dateOfBirth, bloodGroup, mobile,
                whatsappMobile, area, status, role, dhowanPani, fatherName, profession,
                    sthanak, interest, garamPani, photo, subArea);
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

    public List<MemberAllSearchDto> searchMembers(String firstName,
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
                                                  String subArea,
                                                  Boolean familyHead) {

        return memberRepository.searchMembers(firstName, lastName, fatherName, gender, dateOfBirth,
                maritalStatus, bloodGroup, education, permanentAddress, mobile, altMobile,
                whatsappMobile, email, area, currentAddress, profession, professionAddress,
                professionEmail, professionNumber, status, subArea, familyHead);
    }

    // Get prediction for member data using Hugging Face model
    public String getMemberAnalysis(Long memberId) throws JSONException {
        Member member = null;
        Optional<Member> memberData = memberRepository.findById(memberId);
        if (memberData.isPresent()) {
            member = memberData.get();
            //String modelName = "gpt2";  // Replace with the model name you want to use
            String modelName = "facebook/bart-large-cnn";
            //String modelName = "t5-base";
            // Call Hugging Face API to analyze member data
            return huggingFaceService.callHuggingFaceModel(member, modelName);
        }else {
            return "Member not found";
        }
    }

    public void assignUniqueIdsToExistingMembers() {
        List<Member> members = memberRepository.findAll();

        for (Member member : members) {
            if (member.getUniqueMemberId() == null) {
                String formattedId = String.format("SJS%07d", member.getMemberId());
                member.setUniqueMemberId(formattedId);
                memberRepository.save(member);
        }
        }

        System.out.println("✅ Unique IDs assigned to existing members.");
    }
}
