package com.vti.department_service.service;

import com.vti.department_service.entity.Department;
import com.vti.department_service.form.DepartmentFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDepartmentService {
    Page<Department> getDepartmentList(Pageable pageable, String search, DepartmentFilterForm filterForm);
    Department getDepartmentDetail(int id);
}
