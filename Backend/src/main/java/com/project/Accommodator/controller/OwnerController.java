//package com.project.Accommodator.controller;
//import com.project.Accommodator.model.Owner;
//import com.project.Accommodator.service.OwnerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/owner")
//public class OwnerController {
//    @Autowired
//    OwnerService OwnerService;
//    @CrossOrigin
//    @GetMapping("/get/{id}")
//    public Owner getOwnerById(@PathVariable("id") int id) {
//        return OwnerService.getOwnerById(id);
//    }
//    @CrossOrigin
//    @PostMapping("/create")
//    public Owner createOwner(@RequestBody Owner Owner) {
//        return OwnerService.createOwner(Owner);
//    }
//}
