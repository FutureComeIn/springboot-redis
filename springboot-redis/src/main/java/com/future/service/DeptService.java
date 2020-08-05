package com.future.service;

import com.future.entity.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> selectDept();

    public Dept selectByNo(Long deptno);

    public Integer insert(Dept dept);

    public Integer update(Dept dept);
}
