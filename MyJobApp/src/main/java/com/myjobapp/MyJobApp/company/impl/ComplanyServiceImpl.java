package com.myjobapp.MyJobApp.company.impl;

import com.myjobapp.MyJobApp.company.Company;
import com.myjobapp.MyJobApp.company.CompanyRepo;
import com.myjobapp.MyJobApp.company.CompanyService;
import com.myjobapp.MyJobApp.job.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ComplanyServiceImpl implements CompanyService {
    private CompanyRepo companyRepo;

    public ComplanyServiceImpl(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public void saveCompany(Company company) {
        companyRepo.save(company);
    }

    @Override
    public Company updateCompany(Long id, Company updatedCompany) {
        Optional<Company> oldCompanyOptional = companyRepo.findById(id);

        if(oldCompanyOptional.isPresent()){
            Company oldCompany = oldCompanyOptional.get();
            oldCompany.setName(updatedCompany.getName());
            oldCompany.setDescription(updatedCompany.getDescription());
            companyRepo.save(oldCompany);
            return oldCompany;
        }else {
            return null;
        }
    }

    @Override
    public Company fetchCompanyById(Long id) {

        return companyRepo.findById(id).orElse(null);
    }

    @Override
    public boolean removeCompany(Long id) {
        Optional<Company> company = companyRepo.findById(id);
        if(company.isPresent()){
            companyRepo.delete(company.get());
            return true;
        }
        return false;
    }


}
