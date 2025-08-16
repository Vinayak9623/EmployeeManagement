package com.vsd.Repository;

import com.vsd.Dto.Projection.EmployeeBesicProjection;
import com.vsd.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    boolean existsByEmail(String email);

    Page<EmployeeBesicProjection> findAllBy(Pageable pageable);

}
