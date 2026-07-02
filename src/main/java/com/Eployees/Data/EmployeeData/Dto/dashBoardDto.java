package com.Eployees.Data.EmployeeData.Dto;

import lombok.Data;

@Data
public class dashBoardDto {
    private long totalEmployee;
//    private long activeEmployee;
//    private long inActiveEmployee;

    private long totalTickets;
    private long openTickets;
    private long closedTickets;
    private long inProgressTickets;
    private long pendingTickets;

    private long todaySummary;


}
