package com.xlabdynamics.etc.form;

import com.xlabdynamics.etc.dao.WalletDao;

public class IndexForm extends WalletDao {

    private String startingBlock = null;
    private String toAddress = null;

    public String getToAddress() {
        return toAddress = this.getToAddress();
    }

    public String getStartingBlock() {
        return startingBlock = this.getStartingBlock();
    }
}
