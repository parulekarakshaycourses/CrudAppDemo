package com.example.CrudAppDemo.dao;

import com.example.CrudAppDemo.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpDao extends JpaRepository <Emp, Integer>
{

}
