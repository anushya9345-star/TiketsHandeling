package com.Employees.Data.Repository;

import com.Employees.Data.Entity.StatusEnum;
import com.Employees.Data.Entity.bin;
import com.Employees.Data.Entity.tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ticketsRepository extends JpaRepository <tickets, Long>, JpaSpecificationExecutor<tickets>
{
    List<tickets> findBybin(bin bin);
    @Query(value = "SELECT COALESCE(MAX(request_Id),0) + 1 FROM ticket_details", nativeQuery = true)
    long getNextSequence ();
    List<tickets> findBystatus (StatusEnum status);
    List<tickets> findByBinAndStatus (bin bin, StatusEnum status);
    long countByStatus(StatusEnum status);
    List<tickets> findByCreatedDateBetween (LocalDateTime start, LocalDateTime end);
    List<tickets> findByModifiedDateBetween (LocalDateTime start, LocalDateTime end);
}
