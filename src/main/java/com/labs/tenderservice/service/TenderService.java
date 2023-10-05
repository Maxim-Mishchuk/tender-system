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

    //getTenderRepository()??
    public List<Tender> getAllActiveTenders(){
        return getTenderRepository().getActiveTenders();
    }

    //getTenderRepository()??
    public List<Tender> findTenderByKeywords(String keywords){

        return getTenderRepository().getTendersByKeyWords(keywords);
    }

    //getTenderRepository()??
    //tenderRepository.add()?
    //Tender id should be generated in repository
    public void createTender(int id, int userId, String description, String name, Tender.Status status){
        getTenderRepository().getAllTenders().add(
                new Tender(id,  userId,  description,  name, status)
        );
    }

    public void createTender(String name, String description, int userId, String status) {
        //Logic
    }

    //getTenderRepository()??
    public void deleteTender(Tender tender){
        getTenderRepository().deleteTender(tender);
    }

    //getTenderRepository()??
    public Tender getTenderAsOwner(int id){
        return getTenderRepository().getTender(id);
    }

    //getTenderRepository()??
    public void changeStatusOfTender(int id, Tender.Status status){
        getTenderRepository().getTender(id).setStatus(status);
    }


}
