package com.razzolim.junit.services;

import java.util.List;

import com.razzolim.junit.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
 }
