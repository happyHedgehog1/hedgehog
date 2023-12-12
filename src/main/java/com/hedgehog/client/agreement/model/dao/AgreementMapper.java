package com.hedgehog.client.agreement.model.dao;

import com.hedgehog.client.auth.model.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AgreementMapper {
    PostDTO getPrivacyPolicy();

    PostDTO getTermsAndConditions();
}
