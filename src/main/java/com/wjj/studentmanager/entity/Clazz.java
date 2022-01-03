package com.wjj.studentmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("t_clazz")
public class Clazz {
    @TableId(type = IdType.AUTO)
    Integer id;
    String grade;                   //年级
    String clazz;                   //班级
    String headTeacher;             //班主任
    Integer totalStudent;           //限定总人数
    Integer currentTotalStudent;    //当前总人数
}
