package com.Employees.Data.Mappers;

import com.Employees.Data.Dto.dashBoardDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class dashBoardMapping {

    public dashBoardDto dashBoardToDO (long totalEmployee,
//                                       long activeEmployee,
//                                       long inActiveEmployee,
                                       long totalTickets,
                                       long openTickets,
                                       long closedTickets,
                                       long inProgressTickets,
                                       long pendingTickets,
                                       long todaySummary)
    {
        dashBoardDto dashBoardDto = new dashBoardDto();

        dashBoardDto.setTotalEmployee(totalEmployee);
//        dashBoardDto.setActiveEmployee(activeEmployee);
//        dashBoardDto.setInActiveEmployee(inActiveEmployee);

        dashBoardDto.setTotalTickets(totalTickets);
        dashBoardDto.setOpenTickets(openTickets);
        dashBoardDto.setInProgressTickets(inProgressTickets);
        dashBoardDto.setPendingTickets(pendingTickets);
        dashBoardDto.setClosedTickets(closedTickets);

        dashBoardDto.setTodaySummary(todaySummary);

        return dashBoardDto;

    }
}
