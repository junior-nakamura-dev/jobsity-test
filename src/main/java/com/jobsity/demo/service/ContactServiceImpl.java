package com.jobsity.demo.service;

import com.jobsity.demo.entity.Contact;
import com.jobsity.demo.repository.ContactRepositoryExternal;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    private ContactRepositoryExternal contactRepository;
    private ModelMapper modelMapper;

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.getAllContacts()
                .stream()
                .map(value -> {
                    final Contact contact = modelMapper.map(value, Contact.class);
                    contact.setSource("KENECT_LABS");
                    return contact;
                })
                .toList();
    }
}
