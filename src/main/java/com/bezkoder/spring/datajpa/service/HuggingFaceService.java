package com.bezkoder.spring.datajpa.service;

import com.bezkoder.spring.datajpa.model.Member;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.json.JSONObject;

@Service
public class HuggingFaceService {

    private final String API_URL = "https://api-inference.huggingface.co/models/";
    private final String API_KEY = "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";  // Replace with your API key

    // Call Hugging Face Inference API
    public String callHuggingFaceModel(Member modelInput, String modelName) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Content-Type", "application/json");

        // Create a plain text summary of the member object to send to Hugging Face
        String inputText = generateMemberSummary(modelInput);
        // Request body to send to Hugging Face API
        JSONObject requestBody = new JSONObject();
        requestBody.put("inputs", inputText);

        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        // Call Hugging Face model API
        String url = API_URL + modelName;  // Model endpoint
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, String.class);

        return response.getBody();  // Return the AI model's response
    }

    // Generate a plain text summary of the member object
    private String generateMemberSummary(Member member) {
        StringBuilder summary = new StringBuilder();
        summary.append("Summarize the below data: ").append("\n");
        // Assuming the Member object has fields such as name, title, experience, etc.
        summary.append("Name: ").append(member.getFirstName()+ " "+member.getLastName()).append("\n");
        summary.append("Family Head: ").append(member.getFamilyHead() ? "Yes" : "No I am a family member").append("\n");
        summary.append("Date Of Birth: ").append(member.getDateOfBirth()).append("\n");
        summary.append("Gender: ").append(member.getGender()).append("\n");
        summary.append("Marital Status: ").append(member.getMaritalStatus()).append("\n");
        summary.append("Current Address: ").append(member.getCurrentAddress()).append("\n");
        summary.append("Native Place: ").append(member.getAborigine()).append("\n");
        summary.append("Profession: ").append(member.getProfession()).append("\n");
        summary.append("Professional Address: ").append(member.getProfessionAddress()).append("\n");
        summary.append("Blood Group: ").append(member.getBloodGroup()).append("\n");
        summary.append("Area: ").append(member.getArea()).append("\n");
        summary.append("Mobile Number: ").append(member.getMobile()).append("\n");
        summary.append("Whatsapp Number: ").append(member.getWhatsappMobile()).append("\n");
        summary.append("Father's Name: ").append(member.getFatherName()).append("\n");
        summary.append("Role Assigned: ").append(member.getRole()).append("\n");


        //summary.append("Skills: ").append(String.join(", ", member.getSkills())).append("\n");

        // Add more fields as needed
        // Example: summary.append("Education: ").append(member.getEducation()).append("\n");

        return summary.toString();  // Return a formatted string summary of the member's profile
    }
}
