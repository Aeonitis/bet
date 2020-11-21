package com.punter.moneybags.service;

import com.punter.moneybags.model.dao.LiabilityEntry;
import com.punter.moneybags.model.dao.SelectionLiabilityReportOne;
import com.punter.moneybags.model.request.BetCollectionRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository {

    public SelectionLiabilityReportOne calculateSelectionLiability(BetCollectionRequest betCollectionRequest);

    public LiabilityEntry calculateLiability(BetCollectionRequest betCollectionRequest);
}
