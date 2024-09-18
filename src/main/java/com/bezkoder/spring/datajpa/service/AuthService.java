package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.Member;
import com.bezkoder.spring.datajpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.util.List;

@Service
public class AuthService {

    @Autowired
    private MemberRepository memberRepository;

    public Member authenticate(String username, String password) {
//        String[] usernameParts = username.split(" ");
//        if (usernameParts.length != 2) {
//            throw new IllegalArgumentException("Invalid username format.");
//        }

        String lastName = username;
        //String dateOfBirth = usernameParts[1]; // Format should be checked

        // Fetch the member by lastName
        List<Member> members = memberRepository.findByLastName(lastName);

        if (members == null || members.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        for (Member member: members) {
            String outputDate = convertDate(member.getDateOfBirth().toString());
            String expectedPassword = member.getLastName() + outputDate;
            if(password.equalsIgnoreCase(expectedPassword)){
                return member;
            }

        }
        // Combine lastName + dateOfBirth to verify password

            throw new RuntimeException("Invalid password");


         // Return the member details
    }

    public static String convertDate(String inputDate) {
        // Define the format of the input date
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

        // Define the desired output format
        SimpleDateFormat outputFormat = new SimpleDateFormat("ddMMyyyy");

        Date date;
        String formattedDate = "";
        try {
            // Parse the input date string into a Date object
            date = inputFormat.parse(inputDate);

            // Format the Date object into the desired format
            formattedDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the exception
        }

        return formattedDate;
    }
}
