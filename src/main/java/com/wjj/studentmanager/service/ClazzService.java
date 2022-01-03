package com.wjj.studentmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjj.studentmanager.common.ServerResponse;
import com.wjj.studentmanager.entity.Clazz;

public interface ClazzService extends IService<Clazz> {
    ServerResponse addClazz(Clazz clazz);

    ServerResponse listClazz();

    ServerResponse updateClazz(Clazz clazz);

    ServerResponse deleteClazz(Integer id);
}
