package com.jobsity.demo.repository;

import com.jobsity.demo.gateway.KenectGateway;
import com.jobsity.demo.gateway.dto.KenectContact;
import com.jobsity.demo.gateway.dto.KenectContactItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactRepositoryExternal implements ContactRepository<KenectContactItem> {

    @Autowired
    private KenectGateway kenectGateway;

    @Value("${kenect.authentication}")
    private String kenectToken;

    @Override
    public List<KenectContactItem> getAllContacts() {
        int page = 1;
        boolean isEnd = false;
        final List<KenectContactItem> contacts = new ArrayList<>();

        while (!isEnd) {

            try {
                ResponseEntity<KenectContact> responseEntity = kenectGateway.getAllContacts(kenectToken, page);
                if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
                    contacts.addAll(responseEntity.getBody().contacts());
                    page++;
                } else {
                    isEnd = true;
                }
            } catch (Exception exception) {
                isEnd = true;
            }
        }

        return contacts;
    }
}
