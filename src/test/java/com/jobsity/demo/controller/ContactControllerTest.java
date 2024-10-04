package com.jobsity.demo.controller;

import com.jobsity.demo.entity.Contact;
import com.jobsity.demo.service.ContactService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;


@WebMvcTest(controllers = {ContactController.class})
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService service;

    @Test
    public void whenGetAllAssets_thenControlFlowCorrect() throws Exception {
        final Contact contact = new Contact();
        contact.setSource("KENECT_LABS");
        contact.setId(1);
        contact.setName("TEST");
        contact.setEmail("test@gmail.com");
        contact.setCreatedAt(LocalDateTime.now());
        contact.setUpdatedAt(LocalDateTime.now());

        when(this.service.getAllContacts()).thenReturn(List.of(contact));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/contacts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].id")
                                .value(contact.getId())
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].name")
                                .value(contact.getName())
                );

        Mockito.verify(this.service, Mockito.times(1)).getAllContacts();
    }

}