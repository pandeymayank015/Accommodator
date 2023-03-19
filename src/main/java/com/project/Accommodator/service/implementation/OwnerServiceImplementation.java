package com.project.Accommodator.service.implementation;

import com.project.Accommodator.model.Owner;
import com.project.Accommodator.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImplementation implements OwnerService {
    @Autowired
    com.project.Accommodator.repository.OwnerRepository OwnerRepository;

    public OwnerServiceImplementation() {
        super();
    }

    @Override
    public Owner createOwner(Owner Owner) {
        return OwnerRepository.save(Owner);
    }

    @Override
    public Owner getOwnerById(int id) {
        Owner Owner = OwnerRepository.findById(id).orElse(null);
        return Owner;
    }
}