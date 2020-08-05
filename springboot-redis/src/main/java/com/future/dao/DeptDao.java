package com.future.dao;

import com.future.entity.Dept;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DeptDao {

    @Select("select *from dept")
    public List<Dept> selectDept();

    @Select("select *from dept where deptno=#{deptno}")
    public Dept selectByNo(Long deptno);

    @Insert("insert into dept values(0,#{dname},Database())")
    public Integer insert(Dept dept);

    @Update("update dept set dname=#{dname} where deptno=#{deptno}")
    public Integer update(Dept dept);

}
