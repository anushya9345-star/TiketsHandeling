package com.Employees.Data.Service;

import com.Employees.Data.Entity.StatusEnum;
import com.Employees.Data.Entity.binEnum;
import com.Employees.Data.Entity.tickets;

import java.util.List;

public interface ticketsService
{
    tickets createRequest (tickets tickets);
    tickets updateStatus (long requestId, tickets tickets);
    tickets updateBin (long requestId, tickets ticket);
    List <tickets> getByBin (binEnum binName);
    List <tickets> getByEmpId (long empId);
    List <tickets> getByStatus (StatusEnum status);
    List <tickets> getByBinAndStatus (binEnum binName, StatusEnum status);
    void deleteById (long requestId);
}