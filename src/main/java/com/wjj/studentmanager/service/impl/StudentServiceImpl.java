package com.wjj.studentmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjj.studentmanager.common.ResponseEnum;
import com.wjj.studentmanager.common.ServerResponse;
import com.wjj.studentmanager.entity.Student;
import com.wjj.studentmanager.mapper.StudentMapper;
import com.wjj.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    private static final String NUM = "1";
    private static final String NAME = "2";
    @Autowired
    StudentMapper studentMapper;

    /**
     * 添加学生信息
     * @param student
     * @return
     */
    @Override
    public ServerResponse addStudent(Student student) {
        try {
            if(null == student.getName() ||
                    null == student.getAge() ||
                    null == student.getSex()  ||
                    null == student.getNum() ||
                    null == student.getAddress() ||
                    null == student.getGrade() ||
                    null == student.getClazz()){
                return ServerResponse.getInstance().responseEnum(ResponseEnum.INVALID_PARAM);
            }
            studentMapper.insert(student);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.ADD_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    /**
     * 查看所有学生信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse listStudent(Integer pageNum,Integer pageSize) {
        //分页查询
        try {
            Page<Student> studentPage = new Page<>(pageNum,pageSize);
            Page<Student> studentPage1 = studentMapper.selectPage(studentPage, new QueryWrapper<Student>().orderByDesc("name"));
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(studentPage1);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    /**
     * 删除学生信息
     * @param id
     * @return
     */
    @Override
    public ServerResponse deleteStudent(Integer id) {
        try {
            studentMapper.deleteById(id);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    @Override
    public ServerResponse updateStudent(Student student) {
        try {
            studentMapper.updateById(student);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_STUDENT_FAILED);
        }
    }

    /**
     * 根据学生学号或学生姓名来搜索学生信息
     * @param select
     * @param content
     * @return
     */
    @Override
    public ServerResponse searchStudent(String select,String content) {
        try {
            List<Student> students = new ArrayList<>();
            switch (select) {
                case NUM:
                    //根据学号模糊查询学生信息
                    QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
                    studentQueryWrapper.eq("num",content);
                    students = studentMapper.selectList(studentQueryWrapper);
                    break;
                case NAME:
                    //根据姓名模糊查询学生信息
                    QueryWrapper<Student> studentQueryWrapper1 = new QueryWrapper<>();
                    studentQueryWrapper1.eq("name",content);
                    students = studentMapper.selectList(studentQueryWrapper1);
                    break;
            }
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(students);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }

    }
}
