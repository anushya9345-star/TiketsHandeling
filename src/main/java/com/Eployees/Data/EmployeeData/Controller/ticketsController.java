package com.Eployees.Data.EmployeeData.Controller;

import com.Eployees.Data.EmployeeData.Entity.tickets;
import com.Eployees.Data.EmployeeData.Service.ticketsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<tickets> createTickets (@RequestBody tickets tickets)
    {
        return new ResponseEntity<>(ticketsService.createRequest(tickets), HttpStatus.CREATED);
    }

    @PutMapping("/updateStatus/{requestId}")
    public ResponseEntity<tickets>  updateStatus (@PathVariable long requestId, @RequestBody tickets tickets)
    {
         return new ResponseEntity<>(ticketsService.updateStatus(requestId, tickets), HttpStatus.OK);
    }

    @PutMapping("/updateBin/{requestId}")
    public ResponseEntity<tickets>  updateBin (@PathVariable long requestId, @RequestBody tickets ticket)
    {
        return new ResponseEntity<>(ticketsService.updateBin(requestId, ticket), HttpStatus.OK);
    }
    @GetMapping("/reqByBin")
    public ResponseEntity<List<tickets>> getByBinNamee(@RequestParam String binName)
    {
        return new ResponseEntity<>(ticketsService.getByAssignedBin(binName), HttpStatus.OK);
    }

    @GetMapping("/reqById")
    public ResponseEntity<List<tickets>> getByEmpId (@RequestParam long empId)
    {
        return new ResponseEntity<>(ticketsService.getByEmpId(empId), HttpStatus.OK);
    }
}
