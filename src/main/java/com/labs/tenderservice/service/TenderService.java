package com.labs.tenderservice.service;

import com.labs.tenderservice.repository.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenderService {
    private final TenderRepository tenderRepository;
    @Autowired
    public TenderService(TenderRepository tenderRepository) {
        this.tenderRepository = tenderRepository;
    }

    public List<Tender> getAllTenders() {
        return tenderRepository.getAllTenders();
    }

    public Tender getTenderById(long id){
        return tenderRepository.getTender(id);
    }


    public List<Tender> getAllActiveTenders(){
        return tenderRepository.getActiveTenders();
    }


    public List<Tender> findTenderByKeywords(String keywords){

        return tenderRepository.getTendersByKeyWords(keywords);
    }




    public void createTender(String name, String description, long userId) {

        tenderRepository.addTender(userId, description, name);
    }


    public void deleteTender(Tender tender){
        tenderRepository.deleteTender(tender);
    }


    public Tender getTenderAsOwner(long id){
        return tenderRepository.getTender(id);
    }


    public void changeStatusOfTender(long id, String status){
        tenderRepository.getTender(id).setStatus(Tender.Status.valueOf(status));
    }


}
