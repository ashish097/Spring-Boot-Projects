package com.boot.RestWithSpringBoot.controller;

import com.boot.RestWithSpringBoot.model.Package;
import com.boot.RestWithSpringBoot.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PackageController {

    @Autowired
    private PackageRepository packageRepository;

    @GetMapping("/packages")
    public List<Package> getAllPackages() {
       return packageRepository.findAll();
    }

    @GetMapping("/packages/{id}")
    public Package getPackage(@PathVariable int id) {
        return packageRepository.findById(id);
    }

    @PostMapping("/packages")
    public void createPackage(@RequestBody Package pkg) {
        packageRepository.save(pkg);
    }

    @DeleteMapping("/packages/{id}")
    public Package deletePackage(@PathVariable int id) {
        return packageRepository.deleteById(id);
    }
    @PutMapping("/packages/{id}")
    public ResponseEntity<Package> update(@PathVariable int id, @RequestBody Package body){
        
    	Package p = packageRepository.findById(id);
    	
        p.setName(body.getName());
        p.setInstalled(body.getInstalled());
        p.setPackageAuthor(body.getPackageAuthor());
        p.setLicense(body.getLicense());
      
        packageRepository.update(p);
        return  new ResponseEntity<Package>(body, HttpStatus.OK);
    }
    
}
