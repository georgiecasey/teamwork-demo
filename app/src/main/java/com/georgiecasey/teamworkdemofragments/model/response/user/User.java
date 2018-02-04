
package com.georgiecasey.teamworkdemofragments.model.response.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("account")
    @Expose
    private Account account;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
