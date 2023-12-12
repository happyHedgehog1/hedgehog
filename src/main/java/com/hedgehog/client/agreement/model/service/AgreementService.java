package com.hedgehog.client.agreement.model.service;

import com.hedgehog.client.agreement.model.dao.AgreementMapper;
import com.hedgehog.client.auth.model.dto.PostDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AgreementService {
    private final AgreementMapper mapper;
    public PostDTO getPrivacyPolicy() {
        return mapper.getPrivacyPolicy();
    }

    public PostDTO getTermsAndConditions() {
        return mapper.getTermsAndConditions();
    }
}
