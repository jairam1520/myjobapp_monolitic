package com.myjobapp.MyJobApp.company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getAllCompanies();

    void saveCompany(Company company);

    Company updateCompany(Long id, Company updatedCompany);

    Company fetchCompanyById(Long id);

    boolean removeCompany(Long id);
}
