package com.Eployees.Data.EmployeeData.Controller;

import com.Eployees.Data.EmployeeData.Entity.StatusEnum;
import com.Eployees.Data.EmployeeData.Entity.binEnum;
import com.Eployees.Data.EmployeeData.Entity.tickets;
import com.Eployees.Data.EmployeeData.Service.ticketsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")

public class ticketsController
{

    private final ticketsService ticketsService;
    public ticketsController (ticketsService ticketsService)
    {
        this.ticketsService = ticketsService;
    }

    @PostMapping("/create")
    public ResponseEntity<tickets> createTickets (@RequestBody tickets tickets)
    {
        return new ResponseEntity<>(ticketsService.createRequest(tickets), HttpStatus.CREATED);
    }

    @PutMapping("/updateStatusById/{requestId}")
    public ResponseEntity<tickets>  updateStatus (@PathVariable long requestId, @RequestBody tickets tickets)
    {
         return new ResponseEntity<>(ticketsService.updateStatus(requestId, tickets), HttpStatus.OK);
    }

    @PutMapping("/updateBinById/{requestId}")
    public ResponseEntity<tickets>  updateBin (@PathVariable long requestId, @RequestBody tickets ticket)
    {
        return new ResponseEntity<>(ticketsService.updateBin(requestId, ticket), HttpStatus.OK);
    }

    @GetMapping("/getByBinName")
    public ResponseEntity<List<tickets>> getByBinNamee(@RequestParam binEnum binName)
    {
        return new ResponseEntity<>(ticketsService.getByBin(binName), HttpStatus.OK);
    }

    @GetMapping("/getById")
    public ResponseEntity<List<tickets>> getByEmpId (@RequestParam long empId)
    {
        return new ResponseEntity<>(ticketsService.getByEmpId(empId), HttpStatus.OK);
    }

    @GetMapping("/getByBin/{binName}/getByStatus")
    public ResponseEntity<List<tickets>> getByBinAndStatus (@PathVariable binEnum binName, @RequestParam StatusEnum status)
    {
        return new ResponseEntity<>(ticketsService.getByBinAndStatus(binName, status), HttpStatus.OK);
    }

    @GetMapping("/getByStatus")
    public ResponseEntity<List <tickets>> getByStatus (@RequestParam StatusEnum status)
    {
        return new ResponseEntity<>(ticketsService.getByStatus(status), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public void deleteById (@RequestParam long requestId)
    {
        ticketsService.deleteById(requestId);
    }

}
