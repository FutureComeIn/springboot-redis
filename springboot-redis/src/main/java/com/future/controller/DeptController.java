package com.future.controller;

import com.future.entity.Dept;
import com.future.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping(value="/depts",method= RequestMethod.GET)
    public List<Dept> getDept(){
        return deptService.selectDept();
    }

    @RequestMapping(value="/getDept",method=RequestMethod.GET)
    public Dept getDeptByNo(Long deptno){
        return deptService.selectByNo(deptno);
    }

    @RequestMapping(value="/insert",method=RequestMethod.GET)
    public Integer insert(Dept dept){
        return deptService.insert(dept);
    }

    @RequestMapping(value="/update",method=RequestMethod.GET)
    public Integer update(Dept dept){
        return deptService.update(dept);
    }

}
