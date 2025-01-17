package com.accountbook.domain.entity;

import com.accountbook.domain.enums.AssetType;
import com.accountbook.domain.enums.EventType;
import com.accountbook.dto.EcoEvent.EcoEventRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EcoEvent extends BaseInfo{

    @Id
    @GeneratedValue
    @Column(name = "ECO_EVENT_SEQ")
    private Long seq;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private LocalDateTime useDate;
    private Long amount;

    @Enumerated(EnumType.STRING)
    private AssetType assetType;

    @OneToOne
    @JoinColumn(name = "CATEGORY_SEQ")
    private Category category;

    //생성 메소드
    public static EcoEvent createEcoEvent(EcoEventRequest ecoEventRequest, Category category){
        EcoEvent ecoEvent = new EcoEvent();
        ecoEvent.eventType = ecoEventRequest.getEventType();
        ecoEvent.useDate = ecoEventRequest.getUseDate();
        ecoEvent.amount = ecoEventRequest.getAmount();
        ecoEvent.assetType = ecoEventRequest.getAssetType();
        ecoEvent.category = category;
        return ecoEvent;
    }

    //비즈니스 로직
    public void changeEcoEvent(EcoEventRequest ecoEventRequest, Category category){
        this.eventType = ecoEventRequest.getEventType();
        if(ecoEventRequest.getUseDate() != null) {
            this.useDate = ecoEventRequest.getUseDate();
        }
        this.amount = ecoEventRequest.getAmount();
        this.assetType = ecoEventRequest.getAssetType();
        this.category = category;
    }
}
