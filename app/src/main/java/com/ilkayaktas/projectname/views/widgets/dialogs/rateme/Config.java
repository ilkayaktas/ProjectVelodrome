package com.ilkayaktas.projectname.views.widgets.dialogs.rateme;
/**
 * Created by iaktas on 10.12.2015.
 */
/**
 * RateThisApp configuration.
 */
public class Config {
    protected int mCriteriaInstallDays;
    protected int mCriteriaLaunchTimes;
    protected int mTitleId = 0;
    protected int mMessageId = 0;

    /**
     * Constructor with default criteria.
     */
    public Config() {
        this(7, 10);
    }

    /**
     * Constructor.
     * @param criteriaInstallDays
     * @param criteriaLaunchTimes
     */
    public Config(int criteriaInstallDays, int criteriaLaunchTimes) {
        this.mCriteriaInstallDays = criteriaInstallDays;
        this.mCriteriaLaunchTimes = criteriaLaunchTimes;
    }

    /**
     * Set title string ID.
     * @param stringId
     */
    public void setTitle(int stringId) {
        this.mTitleId = stringId;
    }

    /**
     * Set message string ID.
     * @param stringId
     */
    public void setMessage(int stringId) {
        this.mMessageId = stringId;
    }
}