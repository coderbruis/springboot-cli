package com.springcli.ssoauthdemo.mapper;

import com.springcli.ssoauthdemo.model.dataobject.PermissionDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface PermissionMapper extends MyMapper<PermissionDO> {
    List<PermissionDO> selectPermissionsByUserId(@Param("userId") Long userId);
}