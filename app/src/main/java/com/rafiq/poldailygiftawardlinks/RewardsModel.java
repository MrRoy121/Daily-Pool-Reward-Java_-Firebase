package com.rafiq.poldailygiftawardlinks;

import java.sql.Timestamp;
import java.util.Date;

public class RewardsModel {
    String rewardLink;
    Date rewardDate;

    public Date  getRewardDate() {
        return rewardDate;
    }

    public void setRewardDate(Timestamp rewardDate) {
        this.rewardDate = rewardDate;
    }

    public RewardsModel(String rewardLink, Date  rewardDate) {
        this.rewardLink = rewardLink;
        this.rewardDate = rewardDate;
    }

    public String getRewardLink() {
        return rewardLink;
    }

    public void setRewardLink(String rewardLink) {
        this.rewardLink = rewardLink;
    }
}
