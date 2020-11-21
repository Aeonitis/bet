package com.punter.moneybags.service;

import com.punter.moneybags.model.request.BetCollectionRequest;
import com.punter.moneybags.model.response.LiabilityResponse;
import com.punter.moneybags.model.response.TotalLiabilityResponse;

public class BetService implements  BetRepository{

    @Override
    public LiabilityResponse calculateLiability(BetCollectionRequest betCollectionRequest) {
        return null;
    }

    @Override
    public TotalLiabilityResponse calculateTotalLiability(BetCollectionRequest betCollectionRequest) {
        return null;
    }
}
