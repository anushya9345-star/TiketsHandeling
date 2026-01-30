package com.Eployees.Data.EmployeeData.Service;

import com.Eployees.Data.EmployeeData.Entity.StatusEnum;
import com.Eployees.Data.EmployeeData.Entity.bin;
import com.Eployees.Data.EmployeeData.Entity.employee;
import com.Eployees.Data.EmployeeData.Entity.tickets;
import com.Eployees.Data.EmployeeData.Repository.ticketsRepository;
import com.Eployees.Data.EmployeeData.Repository.binRepository;
import com.Eployees.Data.EmployeeData.Repository.employeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ticketsServiceImp implements ticketsService
{
    private final ticketsRepository ticketsRepository;
    private final binRepository binRepository;
    private final employeeRepository employeeRepository;

    public ticketsServiceImp(ticketsRepository ticketsRepository, binRepository binRepository, employeeRepository employeeRepository)
    {
        this.ticketsRepository = ticketsRepository;
        this.binRepository = binRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public tickets createRequest (tickets tickets)
    {
        long nextNumber = ticketsRepository.getNextSequence();
        String requestCode = String.format("WO%08d", nextNumber);
        bin assignedBin = binRepository.findBybinName(tickets.getBinName());
        tickets.setBin(assignedBin);
        tickets.setRequestCode (requestCode);
        return ticketsRepository.save(tickets);
    }

    @Override
    public tickets updateStatus (long requestId, tickets tickets)
    {
        tickets existing = ticketsRepository.findById(requestId).orElseThrow(()->new RuntimeException("Request Is doesn't match"));
        existing.setStatus(tickets.getStatus());

        if(tickets.getStatus() == StatusEnum.Pending  || tickets.getStatus() == StatusEnum.Close)
        {

            if(tickets.getStatusReason()==null || tickets.getStatusReason().isBlank())
            {
                throw new IllegalArgumentException("Reason is required");
            }
            existing.setStatusReason(tickets.getStatusReason());
        }
        else if (tickets.getStatus() == StatusEnum.Resolved)
        {
            validateResolutionNotes(tickets.getNotes());
            existing.setNotes(tickets.getNotes());
            existing.setStatusReason(null);

        } else
        {
            existing.setStatusReason(null);
        }

        return ticketsRepository.save(existing);
    }

    @Override
    public tickets updateBin (long requestId, tickets ticket)
    {
        tickets existing = ticketsRepository.findById(requestId).orElseThrow(()->new IllegalArgumentException("Requst is not created yet!"));
        bin assignedBin = binRepository.findBybinName(ticket.getBinName());
        existing.setBin(assignedBin);
        existing.setBinName(assignedBin.getBinName());
        return ticketsRepository.save(existing);
    }

    @Override
    public List<tickets> getByAssignedBin(String binName)
    {
        return ticketsRepository.findByAssignedBin(binName);
    }

    @Override
    public List <tickets> getByEmpId(long empId)
    {
        employee existingEmp = employeeRepository.findById(empId).orElseThrow(()-> new IllegalArgumentException("Employee doesn't exist"));
        String existingBin = existingEmp.getBinName();
        return ticketsRepository.findByAssignedBin(existingBin);
    }

    private void validateResolutionNotes(String notes)
    {
        if (notes == null || notes.isBlank())
        {
            throw new IllegalArgumentException("Resolution Notes is required");
        }

        String requiredFormate =
                "Problem Reported: .*"+
                        "Problem Analized: .*"+
                        "Action Taken: .*"+
                        "Remark: .*"+
                        "Regards: .*";

        if(!notes.matches("(?s).*" + requiredFormate + ".*"))
        {
            throw new IllegalArgumentException(
                    "Notes formate must contains:"+
                            "Problem Reported: "+
                    ".*Problem Analized: "+
                            ".*Action Taken:"+
                            ".*Remark: "+
                            ".*Regards: "
            );
        }
    }

}