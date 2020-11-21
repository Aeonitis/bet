package com.punter.moneybags.service;

import com.punter.moneybags.model.request.BetCollectionRequest;
import com.punter.moneybags.model.response.LiabilityResponse;
import com.punter.moneybags.model.response.TotalLiabilityResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository {

    public LiabilityResponse calculateLiability(BetCollectionRequest betCollectionRequest);

    public TotalLiabilityResponse calculateTotalLiability(BetCollectionRequest betCollectionRequest);
}
