package com.Employees.Data.Service;

import com.Employees.Data.Entity.bin;

import java.util.List;

public interface binService
{
    bin createBin(bin bin);
    bin updateBin(String binId, bin bin);
    bin getBin (String binId);
    List<bin> getAllBins ();
    void deleteBin (String binId);
}
