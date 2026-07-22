package com.Eployees.Data.EmployeeData.Controller;

import com.Eployees.Data.EmployeeData.Entity.StatusEnum;
import com.Eployees.Data.EmployeeData.Entity.bin;
import com.Eployees.Data.EmployeeData.Entity.binEnum;
import com.Eployees.Data.EmployeeData.Entity.tickets;
import com.Eployees.Data.EmployeeData.Repository.binRepository;
import com.Eployees.Data.EmployeeData.Repository.ticketsRepository;
import com.Eployees.Data.EmployeeData.Service.ticketsService;

import com.Eployees.Data.EmployeeData.Specification.ticketSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/tickets")

public class ticketsController
{

    private final ticketsService ticketsService;
    private final ticketsRepository ticketsRepository;
    private final binRepository binRepository;
    public ticketsController (ticketsService ticketsService, ticketsRepository ticketsRepository, binRepository binRepository)
    {
        this.ticketsService = ticketsService;
        this.ticketsRepository = ticketsRepository;
        this.binRepository = binRepository;
    }

    @PreAuthorize("hasAnyRole('Admin','Engineer', 'User')")
    @PostMapping("/create")
    public ResponseEntity<tickets> createTickets (@RequestBody tickets tickets)
    {
        return new ResponseEntity<>(ticketsService.createRequest(tickets), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Engineer')")
    @PutMapping("/updateStatusById/{requestId}")
    public ResponseEntity<tickets>  updateStatus (@PathVariable long requestId, @RequestBody tickets tickets)
    {
         return new ResponseEntity<>(ticketsService.updateStatus(requestId, tickets), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Engineer')")
    @PutMapping("/updateBinById/{requestId}")
    public ResponseEntity<tickets>  updateBin (@PathVariable long requestId, @RequestBody tickets ticket)
    {
        return new ResponseEntity<>(ticketsService.updateBin(requestId, ticket), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin','Engineer')")
    @GetMapping("/getByBinName")
    public ResponseEntity<List<tickets>> getByBinNamee(@RequestParam binEnum binName)
    {
        return new ResponseEntity<>(ticketsService.getByBin(binName), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin','Engineer')")
    @GetMapping("/getById")
    public ResponseEntity<List<tickets>> getByEmpId (@RequestParam long empId)
    {
        return new ResponseEntity<>(ticketsService.getByEmpId(empId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Engineer')")
    @GetMapping("/getByBin/{binName}/getByStatus")
    public ResponseEntity<List<tickets>> getByBinAndStatus (@PathVariable binEnum binName, @RequestParam StatusEnum status)
    {
        return new ResponseEntity<>(ticketsService.getByBinAndStatus(binName, status), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin','Engineer')")
    @GetMapping("/getByStatus")
    public ResponseEntity<List <tickets>> getByStatus (@RequestParam StatusEnum status)
    {
        return new ResponseEntity<>(ticketsService.getByStatus(status), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/deleteById")
    public void deleteById (@RequestParam long requestId)
    {
        ticketsService.deleteById(requestId);
    }

    @GetMapping("/search")
    public ResponseEntity <?> searchTickets(@RequestParam(required = false) binEnum binName,
                                                  @RequestParam(required = false) String binId,
                                                  @RequestParam(required = false) StatusEnum status,
                                                  @RequestParam(required = false) String createdDate,
                                                  @RequestParam(required = false) String modifiedDate)
    {
        List <tickets> existing = null;
        if (binName != null)
        {
            existing = ticketsRepository.findBybin(binRepository.findBybinName(binName));
        }
        else if (binId != null && !binId.isBlank())
        {
            existing = ticketsRepository.findBybin(binRepository.findById(binId).orElseThrow(()-> new IllegalArgumentException("Bin Id Doesn't Match :(")));
        }
        else if (status != null)
        {
            existing = ticketsRepository.findBystatus(status);
        }
        else if (createdDate != null)
        {
            LocalDate date  = LocalDate.parse(createdDate);
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(LocalTime.MAX);
            existing = ticketsRepository.findByCreatedDateBetween(start, end);
        }
        else if (modifiedDate != null)
        {
            LocalDate date = LocalDate.parse(modifiedDate);
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(LocalTime.MAX);
            existing = ticketsRepository.findByModifiedDateBetween(start, end);
        }

        if (binName == null && binId == null && status == null && createdDate == null && modifiedDate == null)
        {
            return ResponseEntity.badRequest().body("Enter the correct parameter !!");
        }

        if (existing == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket's with requested match can't be found :(");
        }
        else {
            return ResponseEntity.ok().body(existing);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity <?> getTicketsByFiltering (@RequestParam(required = false) StatusEnum status,
                                                     @RequestParam(required = false) String binId,
                                                     @RequestParam(required = false) binEnum binName,
                                                     @RequestParam(required = false) String createdDate,
                                                     @RequestParam(required = false) String modifiedDate,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "requestId") String sortBy,
                                                     @RequestParam(defaultValue = "ASC") String direction)
    {
        Sort sort = direction.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<tickets> spec = ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
        if(status != null)
        {
            spec = spec.and(ticketSpecification.hasStatus(status));
        }
        if(binId != null)
        {
            bin existing = binRepository.findById(binId).orElseThrow(()-> new IllegalArgumentException("Bin cannot be found :("));
            spec = spec.and(ticketSpecification.hasBin(existing));
        }
        if(binName != null)
        {
            bin existing = binRepository.findBybinName(binName);
            spec = spec.and(ticketSpecification.hasBin(existing));
        }
        if(createdDate != null)
        {
            LocalDate date = LocalDate.parse(createdDate);
            LocalDateTime start= date.atStartOfDay();
            LocalDateTime end = date.atTime(LocalTime.MAX);
            spec = spec.and(ticketSpecification.hasCreatedDate(start, end));
        }
        if (modifiedDate != null && !modifiedDate.isBlank())
        {
            LocalDate date = LocalDate.parse(modifiedDate);
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(LocalTime.MAX);
            spec = spec.and(ticketSpecification.hasModifiedDate(start, end));
        }

        if(status == null && binId == null && binName == null && createdDate == null && modifiedDate == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Enter the correct parameter !!");
        }

        Page<tickets> result = ticketsRepository.findAll(spec, pageable);
        if(result.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data Not Found :(");
        }

        return ResponseEntity.ok(result);
    }
}
