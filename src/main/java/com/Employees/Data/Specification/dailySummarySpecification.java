package com.Employees.Data.Specification;

import com.Employees.Data.Entity.dailySummary;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class dailySummarySpecification {

    public static Specification<dailySummary> hasDate (LocalDateTime start, LocalDateTime end)
    {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("date"),start, end));
    }

    public static Specification<dailySummary> hasBelowDailyCount (int count)
    {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dailyCount"),count));
    }

    public static Specification<dailySummary> hasAboveDailySummary (int count)
    {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dailyCount"), count));
    }
}
