package com.project.Accommodator.service.implementation;

import com.project.Accommodator.model.Owner;
import com.project.Accommodator.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**

 Implementation of the {@link OwnerService} interface that provides methods for creating an owner and retrieving an owner by ID.
 */
@Service
public class OwnerServiceImplementation implements OwnerService {
    @Autowired
    com.project.Accommodator.repository.OwnerRepository OwnerRepository;

    public OwnerServiceImplementation() {
        super();
    }

    /**

     Saves a new owner to the database.
     @return The saved owner object
     */
    @Override
    public Owner createOwner(Owner Owner) {
        return OwnerRepository.save(Owner);
    }

    /**

     Retrieves an owner by ID from the database.
     @param id The ID of the owner to retrieve
     @return The owner object with the specified ID, or null if not found
     */
    @Override
    public Owner getOwnerById(int id) {
        Owner Owner = OwnerRepository.findById(id).orElse(null);
        return Owner;
    }
}