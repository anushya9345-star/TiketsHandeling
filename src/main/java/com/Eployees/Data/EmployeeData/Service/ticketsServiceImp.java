package com.Eployees.Data.EmployeeData.Service;

import com.Eployees.Data.EmployeeData.Entity.StatusEnum;
import com.Eployees.Data.EmployeeData.Entity.employee;
import com.Eployees.Data.EmployeeData.Entity.tickets;
import com.Eployees.Data.EmployeeData.Repository.ticketsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ticketsServiceImp implements ticketsService
{
    private final ticketsRepository ticketsRepository;
    private final employeeService employeeService;

    public ticketsServiceImp(ticketsRepository ticketsRepository, employeeService employeeService)
    {
        this.ticketsRepository = ticketsRepository;
        this.employeeService = employeeService;
    }

    @Override
    public tickets createRequest (tickets tickets)
    {
        long nextNumber = ticketsRepository.getNextSequence();
        String requestCode = String.format("WO%08d", nextNumber);
        employee emp = employeeService.getEmpByBinName(tickets.getBinName());
        tickets.setEmployee(emp);
        tickets.setRequestCode (requestCode);
        return ticketsRepository.save(tickets);
    }

    @Override
    public tickets updateStatus (long requestId, tickets tickets)
    {
        tickets existing = ticketsRepository.findById(requestId).orElseThrow(()->new RuntimeException("Request Is doesn't match"));
        existing.setStatus(tickets.getStatus());

        if(tickets.getStatus() == StatusEnum.Pending )
        {

            if(tickets.getPendingReason()==null || tickets.getPendingReason().isBlank())
            {
                throw new IllegalArgumentException("Reason is required");
            }
            existing.setPendingReason(tickets.getPendingReason());
        }
        else if (tickets.getStatus() == StatusEnum.Resolved)
        {
            validateResolutionNotes(tickets.getNotes());
            existing.setNotes(tickets.getNotes());
            existing.setPendingReason(null);

        } else
        {
            existing.setPendingReason(null);
        }

        return ticketsRepository.save(existing);
    }

    @Override
    public tickets updateBin (long requestId, tickets ticket)
    {
        tickets existing = ticketsRepository.findById(requestId).orElseThrow(()->new IllegalArgumentException("Requst is not created yet!"));
        employee exisEmp = employeeService.getEmpByBinName(ticket.getBinName());
        existing.setEmployee(exisEmp);
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
        return ticketsRepository.findByEmployee_EmpId(empId);
    }

    private void validateResolutionNotes(String notes)
    {
        if (notes == null || notes.isBlank())
        {
            throw new IllegalArgumentException("Resolution Notes is required");
        }

        String requiredFormate =
                "Problem Reported: "+
                        ".*Prolem Analized: "+
                        ".*Action Taken: "+
                        ".*Remark: "+
                        ".*Regards: ";

        if(!notes.matches("(?s).*" + requiredFormate + ".*"))
        {
            throw new IllegalArgumentException(
                    "Notes formate must contains:\n"+
                            "Problem Reported: "+
                    ".*Problem Analized: "+
                            ".*Action Taken:"+
                            ".*Remarks: "+
                            ".*Regards: "
            );
        }
    }

}