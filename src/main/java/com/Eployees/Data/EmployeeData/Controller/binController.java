package com.Eployees.Data.EmployeeData.Controller;

import com.Eployees.Data.EmployeeData.Dto.binDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Eployees.Data.EmployeeData.Service.binService;
import com.Eployees.Data.EmployeeData.Entity.bin;
import com.Eployees.Data.EmployeeData.Repository.binRepository;
import com.Eployees.Data.EmployeeData.Mappers.binMapping;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bin")
public class binController
{
    private final binService binService;
    private final binRepository binRepository;
    private final binMapping binMapping;

    @PostMapping("/create")
    public ResponseEntity<bin> createBin (@RequestBody bin bin)
    {
        return new ResponseEntity<>(binService.createBin(bin), HttpStatus.CREATED);
    }

    @PutMapping("/updateBin/{binId}")
    public ResponseEntity<bin> updateBin (@PathVariable String binId, @RequestBody bin bin)
    {
        return new ResponseEntity<>(binService.updateBin(binId, bin), HttpStatus.OK);
    }

    @GetMapping("/getById/{binId}")
    public ResponseEntity<binDto> getBin (@PathVariable String binId)
    {
        bin existingBin = binRepository.findById(binId).orElseThrow(()-> new IllegalArgumentException("Bin can't be find Please check your bin Id !"));
        System.out.println(existingBin.getEmpName());
        return ResponseEntity.ok(binMapping.binTodo(existingBin));

    }

    @GetMapping ("/getAllBins")
    public List<binDto> getAllBins ()
    {
        return binRepository.findAll()
                .stream()
                .map(binMapping :: binTodo)
                .toList();
    }

    @DeleteMapping("/delete/{binId}")
    public void deleteBin ( @PathVariable String binId)
    {
        binService.deleteBin(binId);
    }

}
