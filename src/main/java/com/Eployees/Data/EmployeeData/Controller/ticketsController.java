package com.Eployees.Data.EmployeeData.Controller;

import com.Eployees.Data.EmployeeData.Entity.tickets;
import com.Eployees.Data.EmployeeData.Service.ticketsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Tickets")

public class ticketsController
{
    private final ticketsService ticketsService;
    public ticketsController (ticketsService ticketsService)
    {
        this.ticketsService = ticketsService;
    }

    @PostMapping("/assign")
    public tickets createTickets (@RequestBody tickets tickets)
    {
        return ticketsService.createRequest(tickets);
    }

    @PutMapping("/updateStatus/{requestId}")
    public tickets updateStatus (@PathVariable long requestId, @RequestBody tickets tickets)
    {
         return ticketsService.updateStatus(requestId, tickets);
    }

    @PutMapping("/updateBin/{requestId}")
    public tickets updateBin (@PathVariable long requestId, @RequestBody tickets ticket)
    {
        return ticketsService.updateBin(requestId, ticket);
    }
    @GetMapping("/reqByBin")
    public List<tickets> getByBinNamee(@RequestParam String binName)
    {
        return ticketsService.getByAssignedBin(binName);
    }

    @GetMapping("/reqById")
    public List<tickets> getByEmpId (@RequestParam long empId)
    {
        return ticketsService.getByEmpId(empId);
    }
}
