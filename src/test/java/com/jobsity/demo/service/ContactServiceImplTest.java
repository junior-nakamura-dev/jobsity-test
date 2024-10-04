package com.jobsity.demo.service;

import com.jobsity.demo.gateway.dto.KenectContactItem;
import com.jobsity.demo.repository.ContactRepositoryExternal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {

    private ContactServiceImpl contactService;

    @Mock
    private ContactRepositoryExternal contactRepository;

    @BeforeEach
    public void setUp() {
        this.contactService = new ContactServiceImpl(contactRepository, new ModelMapper());
    }

    @Test
    public void shouldReturnAllContacts() {
        final KenectContactItem kenectContactItem = new KenectContactItem(1, "TEST", "test@gmail.com", LocalDateTime.now(), LocalDateTime.now());
        when(contactRepository.getAllContacts()).thenReturn(List.of(kenectContactItem));

        final var result = contactService.getAllContacts();

        Assertions.assertEquals(result.size(), 1);

    }

}