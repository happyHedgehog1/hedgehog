package com.hedgehog.admin.adminMember.model.dao;

import com.hedgehog.admin.adminMember.model.dto.adminAllMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.adminMemberForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface adminMemberMapper {


    List<adminAllMemberDTO> selectMember(adminMemberForm form);

    List<adminAllMemberDTO> selectAllMemberList();
}
