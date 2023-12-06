package com.hedgehog.admin.adminMember.model.dao;

import com.hedgehog.admin.adminMember.model.dto.adminAllMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface adminMemberMapper {

    List<adminAllMemberDTO> selectAllMemberList();
}
