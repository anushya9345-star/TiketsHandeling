package com.Eployees.Data.EmployeeData.Service;

import com.Eployees.Data.EmployeeData.Entity.bin;

import java.util.List;

public interface binService
{
    bin createBin(bin bin);
    bin updateBin(String binId, bin bin);
    bin getBin (String binId);
    List<bin> getAllBins ();
    void deleteBin (String binId);
}
