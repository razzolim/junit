package com.razzolim.junit.repositories;


import java.util.List;

import com.razzolim.junit.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
