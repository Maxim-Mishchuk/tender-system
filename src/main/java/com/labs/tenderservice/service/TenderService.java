package com.labs.tenderservice.service;

import com.labs.tenderservice.repository.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.labs.tenderservice.service.Tender;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Tender> getAllActiveTenders(){
        return getTenderRepository().getActiveTenders();
    }

    public List<Tender> findTenderByKeywords(String keywords){

        return getTenderRepository().getTendersByKeyWords(keywords);
    }
    public void createTender(int id, int userId, String description, String name, Tender.Status status){
        getTenderRepository().getAllTenders().add(
                new Tender(id,  userId,  description,  name, status)
        );
    }

    public void deleteTender(Tender tender){
        getTenderRepository().deleteTender(tender);
    }

    public Tender getTenderAsOwner(int id){
        return getTenderRepository().getTender(id);
    }

    public void changeStatusOfTender(int id, Tender.Status status){
        getTenderRepository().getTender(id).setStatus(status);
    }


}
