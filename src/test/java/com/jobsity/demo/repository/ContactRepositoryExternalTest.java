package com.jobsity.demo.repository;

import com.jobsity.demo.gateway.KenectGateway;
import com.jobsity.demo.gateway.dto.KenectContact;
import com.jobsity.demo.gateway.dto.KenectContactItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactRepositoryExternalTest {

    @InjectMocks
    private ContactRepositoryExternal contactRepositoryExternal;

    @Mock
    private KenectGateway kenectGateway;

    @Test
    public void shouldReturnContactsFromAPI() {

        final KenectContactItem kenectContactItem = new KenectContactItem(1, "TEST", "test@gmail.com", LocalDateTime.now(), LocalDateTime.now());
        final KenectContact kenectContact = new KenectContact(List.of(kenectContactItem));
        when(kenectGateway.getAllContacts(any(), anyInt())).thenReturn(ResponseEntity.ok(kenectContact)).thenReturn(ResponseEntity.ofNullable(null));

        final var result = contactRepositoryExternal.getAllContacts();
        assertNotNull(result);
        assertEquals(result.size(), 1);
    }

    @Test
    public void shouldReturnEmptyContactsFromAPI() {
        final var result = contactRepositoryExternal.getAllContacts();
        assertNotNull(result);
        assertEquals(result.size(), 0);
    }

}