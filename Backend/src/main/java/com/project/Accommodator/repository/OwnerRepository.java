package com.project.Accommodator.repository;

import com.project.Accommodator.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Integer> {
    Optional<Owner> findByEmail(String username);
}
