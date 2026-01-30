package com.Eployees.Data.EmployeeData.Service;

import com.Eployees.Data.EmployeeData.Entity.bin;
import com.Eployees.Data.EmployeeData.Repository.binRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class binServiceImp implements binService
{
    private final binRepository binRepository;

    public binServiceImp (binRepository binRepository)
    {
        this.binRepository = binRepository;
    }

    @Override
    public bin createBin (bin bin)
    {
        return binRepository.save(bin);
    }

    @Override
    public bin updateBin (String binId, bin bin)
    {
        bin existingBin = binRepository.findById(binId).orElseThrow(()->new IllegalArgumentException ("Bin can't be found"));
        existingBin.setBinName(bin.getBinName());
        if(existingBin.getEmployee() != null)
        {
            existingBin.setIsActive(true);
        }
        else
        {
            existingBin.setIsActive(false);
        }
        return binRepository.save(existingBin);
    }

    @Override
    public bin getBin (String binId)
    {
        return binRepository.getReferenceById(binId);
    }

    @Override
    public List<bin> getAllBins ()
    {
        return binRepository.findAll();
    }

    @Override
    public void deleteBin (String binId)
    {
        binRepository.deleteById(binId);
    }
}
