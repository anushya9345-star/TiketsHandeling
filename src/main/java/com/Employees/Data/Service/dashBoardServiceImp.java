package com.Employees.Data.Service;

import com.Employees.Data.Dto.dashBoardDto;
import com.Employees.Data.Entity.StatusEnum;
import com.Employees.Data.Repository.binRepository;
import com.Employees.Data.Repository.dailySummaryRepository;
import com.Employees.Data.Repository.employeeRepository;
import com.Employees.Data.Repository.ticketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Employees.Data.Mappers.dashBoardMapping;

@Service
public class dashBoardServiceImp implements dashBoardService {

    @Autowired
    private employeeRepository employeeRepository;

    @Autowired
    private binRepository binRepository;

    @Autowired
    private ticketsRepository ticketsRepository;

    @Autowired
    private dailySummaryRepository dailySummaryRepository;

    @Autowired
    private dashBoardMapping dashBoardMapping;

    @Override
    public dashBoardDto getDashBoardDto ()
    {
        long totalEmployee = employeeRepository.count();

        long totalTickets = ticketsRepository.count();
        long openTickets = ticketsRepository.countByStatus(StatusEnum.Assigned);
        long closedTickets = ticketsRepository.countByStatus(StatusEnum.Close);
        long inProgressTickets = ticketsRepository.countByStatus(StatusEnum.InProgress);
        long pendingTickets= ticketsRepository.countByStatus(StatusEnum.Pending);

        long todaySummary = dailySummaryRepository.getTotalDailyCount();

        return dashBoardMapping.dashBoardToDO(totalEmployee, totalTickets, openTickets, closedTickets, inProgressTickets, pendingTickets, todaySummary);
    }

}
