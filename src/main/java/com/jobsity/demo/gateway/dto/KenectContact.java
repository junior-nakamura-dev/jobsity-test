package com.jobsity.demo.gateway.dto;

import java.util.List;

public record KenectContact(
        List<KenectContactItem> contacts
) {
}
