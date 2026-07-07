package com.vti.account_service.feignclient;

import com.vti.account_service.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department-service", path = "/api/v1")
public interface DepartmentFeignClient {

    @GetMapping("/departments/{id}")
    public DepartmentDto getDepartmentById(@PathVariable("id") int id);
}
