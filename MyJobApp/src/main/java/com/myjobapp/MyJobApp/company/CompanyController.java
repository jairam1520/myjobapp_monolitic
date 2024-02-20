package com.myjobapp.MyJobApp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<String> addJob(@RequestBody Company company){
        companyService.saveCompany(company);
        return new ResponseEntity<String>("Company added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.fetchCompanyById(id);

        if (company!=null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    private ResponseEntity<Company> updateJob(@PathVariable Long id,@RequestBody Company updatedCompany){

        Company company = companyService.updateCompany(id,updatedCompany);
        if(company!=null){
            return new ResponseEntity<>(company,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> removeCompany(@PathVariable Long id){
        if(companyService.removeCompany(id)){
            return new ResponseEntity<String>("Company removed successfully",HttpStatus.OK);
        }
        return new ResponseEntity<String>("Company not found",HttpStatus.NOT_FOUND);
    }
}
