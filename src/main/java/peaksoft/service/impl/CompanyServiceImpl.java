package peaksoft.service.impl;

import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Student;
import peaksoft.config.repository.CompanyRepository;
import peaksoft.config.repository.CourseRepository;
import peaksoft.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CourseRepository courseRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository,
                              CourseRepository courseRepository) {
        this.companyRepository = companyRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(Long id, Company company) {
     Company company1 = companyRepository.getById(id);
     company1.setCompanyName(company.getCompanyName());
     company1.setLocatedCountry(company.getLocatedCountry());
     company1.setCourses(company.getCourses());
     companyRepository.save(company1);
    }

    @Override
    public Company getById(Long id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public void deleteCompany(Company company) {
       companyRepository.delete(company);
    }

    @Override
    public List<Course> getCoursesByCompanyId(Long id) {
        return companyRepository.getCoursesByCompanyId(id);
    }

    @Override
    public List<Student> getStudentsByCompanyId(Long id) {
        return companyRepository.getStudentsByCompanyId(id);
    }


}
