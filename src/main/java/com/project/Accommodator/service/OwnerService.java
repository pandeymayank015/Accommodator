package com.project.Accommodator.service;
import com.project.Accommodator.model.Owner;

/**

 This interface provides methods to perform CRUD operations on Owner entities.
 */
public interface OwnerService {
    Owner createOwner(Owner Owner);

    Owner getOwnerById(int id);

}
