package com.Eployees.Data.EmployeeData.Service;

import com.Eployees.Data.EmployeeData.Dto.dashBoardDto;
import com.Eployees.Data.EmployeeData.Entity.StatusEnum;
import com.Eployees.Data.EmployeeData.Repository.binRepository;
import com.Eployees.Data.EmployeeData.Repository.dailySummaryRepository;
import com.Eployees.Data.EmployeeData.Repository.employeeRepository;
import com.Eployees.Data.EmployeeData.Repository.ticketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Eployees.Data.EmployeeData.Mappers.dashBoardMapping;

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
