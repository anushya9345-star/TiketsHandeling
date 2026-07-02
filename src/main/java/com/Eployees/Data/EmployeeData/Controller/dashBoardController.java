package com.Eployees.Data.EmployeeData.Controller;


import com.Eployees.Data.EmployeeData.Dto.dashBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.Eployees.Data.EmployeeData.Service.dashBoardService;
import com.Eployees.Data.EmployeeData.Mappers.dashBoardMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class dashBoardController {

    private final dashBoardService dashBoardService;
    private final dashBoardMapping dashBoardMapping;

    @GetMapping
    public ResponseEntity<dashBoardDto> getDashBoard()
    {
        return ResponseEntity.ok(dashBoardService.getDashBoardDto());
    }
}
