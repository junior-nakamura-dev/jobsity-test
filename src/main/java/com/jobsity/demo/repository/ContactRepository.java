package com.jobsity.demo.repository;

import java.util.List;

public interface ContactRepository<T> {

    List<T> getAllContacts();

}
