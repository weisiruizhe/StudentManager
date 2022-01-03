package com.wjj.studentmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjj.studentmanager.common.ResponseEnum;
import com.wjj.studentmanager.common.ServerResponse;
import com.wjj.studentmanager.entity.Clazz;
import com.wjj.studentmanager.mapper.ClazzMapper;
import com.wjj.studentmanager.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {

    @Autowired
    ClazzMapper clazzMapper;

    /**
     * 添加班级信息
     * @param clazz
     * @return
     */
    @Override
    public ServerResponse addClazz(Clazz clazz) {
        try {
            if(null == clazz.getCurrentTotalStudent() ||
                    null == clazz.getHeadTeacher() ||
                    null == clazz.getTotalStudent() ||
                    null == clazz.getGrade() ||
                    null == clazz.getClazz()){
                return ServerResponse.getInstance().responseEnum(ResponseEnum.INVALID_PARAM);
            }
            clazzMapper.insert(clazz);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.ADD_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    /**
     * 查询所有班级信息
     * @return
     */
    @Override
    public ServerResponse listClazz() {
        try {
            Page<Clazz> clazzPage = new Page<>();
            Page<Clazz> clazzPage1 = clazzMapper.selectPage(clazzPage, new QueryWrapper<Clazz>().orderByDesc("headTeacher"));
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(clazzPage1);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    /**
     * 修改班级信息
     * @param clazz
     * @return
     */
    @Override
    public ServerResponse updateClazz(Clazz clazz) {
        try {
            clazzMapper.updateById(clazz);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_CLAZZ_FAILED);
        }
    }

    /**
     * 删除班级信息
     * @param id
     * @return
     */
    @Override
    public ServerResponse deleteClazz(Integer id) {
        try {
            clazzMapper.deleteById(id);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.DELETE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }
}
