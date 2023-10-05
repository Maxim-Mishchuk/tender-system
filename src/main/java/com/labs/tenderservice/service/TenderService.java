package com.labs.tenderservice.service;

import com.labs.tenderservice.repository.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenderService {
    private final TenderRepository tenderRepository;
    @Autowired
    public TenderService(TenderRepository tenderRepository) {
        this.tenderRepository = tenderRepository;
    }

    public TenderRepository getTenderRepository() {
        return tenderRepository;
    }


}
