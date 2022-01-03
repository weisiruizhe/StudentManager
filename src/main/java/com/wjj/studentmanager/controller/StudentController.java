package com.wjj.studentmanager.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjj.studentmanager.common.ServerResponse;
import com.wjj.studentmanager.entity.Student;
import com.wjj.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    /**
     * 添加学生信息
     * @param student
     * @return
     */
    @PostMapping ("/add")
    public ServerResponse addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    /**
     * 查看学生信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ServerResponse listStudent(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        return studentService.listStudent(pageNum,pageSize);
    }

    /**
     * 删除学生信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ServerResponse deleteStudent(@PathVariable Integer id){
        return studentService.deleteStudent(id);
    }

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    @PutMapping("update")
    public ServerResponse updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @GetMapping("search")
    public ServerResponse searchStudent(String select,String content){
        return studentService.searchStudent(select,content);
    }
}
