package com.Eployees.Data.EmployeeData.Repository;

import com.Eployees.Data.EmployeeData.Entity.tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ticketsRepository extends JpaRepository <tickets, Long>
{
    List<tickets> findByAssignedBin(String assignedBin);
    @Query(value = "SELECT COALESCE(MAX(request_Id),0) + 1 FROM ticket_details", nativeQuery = true)
    long getNextSequence ();
}
