package com.Eployees.Data.EmployeeData.Service;

import com.Eployees.Data.EmployeeData.Entity.tickets;

import java.util.List;

public interface ticketsService
{
    tickets createRequest (tickets tickets);
    tickets updateStatus (long requestId, tickets tickets);
    tickets updateBin (long requestId, tickets ticket);
    List <tickets> getByAssignedBin(String binName);
    List <tickets> getByEmpId (long empId);
}