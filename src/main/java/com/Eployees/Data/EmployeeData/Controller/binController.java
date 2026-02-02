package com.Eployees.Data.EmployeeData.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Eployees.Data.EmployeeData.Service.binService;
import com.Eployees.Data.EmployeeData.Entity.bin;

import java.util.List;

@RestController
@RequestMapping("/bin")
public class binController
{
    private final binService binService;
    public binController(binService binService)
    {
        this.binService = binService;
    }

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
    public ResponseEntity<bin> getBin (@PathVariable String binId)
    {
        return new ResponseEntity<>(binService.getBin(binId),HttpStatus.OK);
    }

    @GetMapping ("/getAllBins")
    public ResponseEntity<List<bin>> getAllBins ()
    {
        return new ResponseEntity<>(binService.getAllBins(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{binId}")
    public void deleteBin ( @PathVariable String binId)
    {
        binService.deleteBin(binId);
    }

}
