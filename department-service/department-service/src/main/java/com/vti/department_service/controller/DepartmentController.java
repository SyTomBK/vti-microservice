package com.vti.department_service.controller;

import com.vti.department_service.dto.DepartmentDto;
import com.vti.department_service.entity.Department;
import com.vti.department_service.form.DepartmentFilterForm;
import com.vti.department_service.service.IDepartmentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService _departmentService;

    @Autowired
    private ModelMapper _mapper;

    @GetMapping()
    public Page<DepartmentDto> getDepartmentList(
            Pageable pageable,
            @RequestParam(name = "search", required = false) String search,
            DepartmentFilterForm filterForm) {
            Page<Department> entity = _departmentService.getDepartmentList(pageable, search, filterForm);

            List<DepartmentDto> dtoList = _mapper.map(entity.getContent(),
                    new TypeToken<List<DepartmentDto>>(){}.getType());

            Page<DepartmentDto> dtoPage = new PageImpl<>(dtoList, pageable, entity.getTotalElements());
            return dtoPage;
    }

    @GetMapping(value = "{id}")
    public DepartmentDto getDepartmentById(@PathVariable(name = "id") int id) {
        Department entity = _departmentService.getDepartmentDetail(id);

        DepartmentDto dto = _mapper.map(entity, DepartmentDto.class);
        return dto;
    }

















}
