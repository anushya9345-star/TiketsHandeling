package com.Employees.Data.Specification;

import com.Employees.Data.Entity.StatusEnum;
import com.Employees.Data.Entity.bin;
import com.Employees.Data.Entity.tickets;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class ticketSpecification {
    public static Specification<tickets> hasStatus (StatusEnum status)
    {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status));
    }
    public static Specification<tickets> hasBin (bin bin)
    {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("bin"), bin));
    }
    public static Specification<tickets> hasCreatedDate (LocalDateTime start, LocalDateTime end)
    {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("createdDate"), start, end));
    }
    public static Specification<tickets> hasModifiedDate (LocalDateTime start, LocalDateTime end)
    {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("modifiedDate"), start, end));
    }
}
