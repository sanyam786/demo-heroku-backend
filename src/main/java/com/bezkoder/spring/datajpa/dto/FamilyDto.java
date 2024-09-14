package com.bezkoder.spring.datajpa.dto;

import com.bezkoder.spring.datajpa.model.Member;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.List;

public class FamilyDto {
    private Long familyId;

    private List<Member> members;
}
