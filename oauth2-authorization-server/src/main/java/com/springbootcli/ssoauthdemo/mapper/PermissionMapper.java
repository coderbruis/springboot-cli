package com.springbootcli.ssoauthdemo.mapper;

import com.springbootcli.ssoauthdemo.model.dataobject.PermissionDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface PermissionMapper extends MyMapper<PermissionDO> {
    List<PermissionDO> selectPermissionsByUserId(@Param("userId") Long userId);
}