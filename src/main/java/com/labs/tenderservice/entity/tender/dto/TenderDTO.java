package com.labs.tenderservice.entity.tender.dto;

import com.labs.tenderservice.entity.proposition.dto.PropositionDTO;
import com.labs.tenderservice.entity.tender.Tender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class TenderDTO {
    @PositiveOrZero
    private final long id;
    @PositiveOrZero
    private final long userId;
    @Pattern(regexp = "\\w+]")
    private final String url;
    @NotBlank
    private final String name;
    private final String description;
    @NotNull
    private final Tender.Status status;
    private final List<PropositionDTO> propositionList;

    public static TenderDTO getDTO(Tender tender) {
        return new TenderDTO(
                tender.getId(),
                tender.getUser().getId(),
                tender.getTenderUrlConnector().getUrl(),
                tender.getName(),
                tender.getDescription(),
                tender.getStatus(),
                PropositionDTO.getList(tender.getPropositionList())
        );
    }

    public static TenderDTO getBasicDTO(Tender tender) {
        return new TenderDTO(
                tender.getId(),
                tender.getUser().getId(),
                tender.getTenderUrlConnector().getUrl(),
                tender.getName(),
                tender.getDescription(),
                tender.getStatus(),
                null
        );
    }

    public static List<TenderDTO> getList(List<Tender> tenderList) {
        return tenderList.stream()
                .map(TenderDTO::getBasicDTO)
                .toList();
    }
}
