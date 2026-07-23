package com.Employees.Data.Controller;

import com.Employees.Data.Dto.binDto;
import com.Employees.Data.Entity.binEnum;
import com.Employees.Data.Repository.employeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.Employees.Data.Service.binService;
import com.Employees.Data.Entity.bin;
import com.Employees.Data.Repository.binRepository;
import com.Employees.Data.Mappers.binMapping;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bin")
public class binController
{
    private final binService binService;
    private final binRepository binRepository;
    private final binMapping binMapping;
    private final employeeRepository employeeRepository;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/create")
    public ResponseEntity<bin> createBin (@RequestBody bin bin)
    {
        return new ResponseEntity<>(binService.createBin(bin), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/updateBin/{binId}")
    public ResponseEntity<bin> updateBin (@PathVariable String binId, @RequestBody bin bin)
    {
        return new ResponseEntity<>(binService.updateBin(binId, bin), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Engineer')")
    @GetMapping("/getById/{binId}")
    public ResponseEntity<binDto> getBin (@PathVariable String binId)
    {
        bin existingBin = binRepository.findById(binId).orElseThrow(()-> new IllegalArgumentException("Bin can't be find Please check your bin Id !"));
        System.out.println(existingBin.getEmpName());
        return ResponseEntity.ok(binMapping.binTodo(existingBin));

    }

    @PreAuthorize("hasAnyRole('Admin', 'Engineer')")
    @GetMapping ("/getAllBins")
    public List<binDto> getAllBins ()
    {
        return binRepository.findAll()
                .stream()
                .map(binMapping :: binTodo)
                .toList();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBin (@RequestParam(required = false) binEnum binName,
                                        @RequestParam(required = false) String binId,
                                        @RequestParam(required = false) String empName)
    {
        bin existingBin = null ;
        if (binName != null )
        {
            existingBin = binRepository.findBybinName(binName);
        }
        else if (binId != null && !binId.isBlank())
        {
            existingBin = binRepository.findById(binId).orElseThrow(()-> new IllegalArgumentException("Bin Cannot be found which is associate with this Bin Id, Kindly check the Bin ID once and try again"));
        }
        else if (empName != null && !empName.isBlank())
        {
            existingBin = binRepository.findByEmployee(employeeRepository.findByEmpName(empName));
        }

        if (binName == null && binId == null && empName == null)
        {
            return ResponseEntity.badRequest().body("Please Enter the correcr parameter");
        }

        if(existingBin == null)
        {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No results :(");
        }

        return ResponseEntity.ok(binMapping.binTodo(existingBin));
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/delete/{binId}")
    public void deleteBin ( @PathVariable String binId)
    {
        binService.deleteBin(binId);
    }

}
