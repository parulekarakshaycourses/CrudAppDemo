package com.example.CrudAppDemo.controller;

import com.example.CrudAppDemo.dao.EmpDao;
import com.example.CrudAppDemo.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmpController
{
    @Autowired
    EmpDao empDao;

    @GetMapping("/")
    public String home(Model model)
    {
        int curPage = 1;
        int maxSize = 5;

        Pageable pageable = PageRequest.of(curPage-1, maxSize, Sort.by("salary"));
        Page<Emp> page = empDao.findAll(pageable);
        int totalPages = page.getTotalPages();

        if(totalPages < 1)
        {
            totalPages = 1;
        }

        List<Emp> listEmp = page.toList();
        model.addAttribute("listEmp", listEmp);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", totalPages);
        return "index";
    }

    @GetMapping("/{curPage}/")
    public String findByPageNo(Model model, @PathVariable int curPage)
    {
        int maxSize = 5;

        Pageable pageable = PageRequest.of(curPage-1, maxSize, Sort.by("salary"));
        Page<Emp> page = empDao.findAll(pageable);
        int totalPages = page.getTotalPages();

        if(totalPages < 1)
        {
            totalPages = 1;
        }

        List<Emp> listEmp = page.toList();
        model.addAttribute("listEmp", listEmp);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", totalPages);

        return "index";
    }

    @GetMapping("/emp/delete/{id}/")
    public String deleteEmp(Model model, @PathVariable int id)
    {
        empDao.deleteById(id);

        int curPage = 1;
        int maxSize = 5;

        Pageable pageable = PageRequest.of(curPage-1, maxSize, Sort.by("salary"));
        Page<Emp> page = empDao.findAll(pageable);
        int totalPages = page.getTotalPages();

        if(totalPages < 1)
        {
            totalPages = 1;
        }

        List<Emp> listEmp = page.toList();
        model.addAttribute("listEmp", listEmp);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", totalPages);

        return "index";
    }

    @PostMapping("/emp/save/")
    public String saveEmp(Model model, Emp emp)
    {
        empDao.save(emp);

        int curPage = 1;
        int maxSize = 5;

        Pageable pageable = PageRequest.of(curPage-1, maxSize, Sort.by("salary"));
        Page<Emp> page = empDao.findAll(pageable);
        int totalPages = page.getTotalPages();

        if(totalPages < 1)
        {
            totalPages = 1;
        }

        List<Emp> listEmp = page.toList();
        model.addAttribute("listEmp", listEmp);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", totalPages);

        return "index";
    }

    @GetMapping("/emp/{id}/")
    public String getEmpById(Model model, @PathVariable int id)
    {
        model.addAttribute("emp", empDao.getReferenceById(id));
        return "empDetails";
    }

}
