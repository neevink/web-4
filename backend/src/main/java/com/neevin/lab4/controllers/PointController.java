package com.neevin.lab4.controllers;

import com.neevin.lab4.models.Point;
import com.neevin.lab4.services.PointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class PointController {

    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService=pointService;
    }
    @CrossOrigin
    @PostMapping("/checkPoint")
    public ResponseEntity<String> check(@RequestBody Map<String, String> request){
        return pointService.check(request);
    }
    @CrossOrigin
    @GetMapping("/Points/{userName}")
    public List<Point> test (@PathVariable String userName) {
        return pointService.getPoint(userName);
    }
    @CrossOrigin
    @Transactional
    @DeleteMapping("/Table/{userName}")
    public void dropTable (@PathVariable String userName) {
        pointService.dellPoint(userName);
    }

}
