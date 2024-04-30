package com.xlabdynamics.etc.dao;

import java.util.ArrayList;

public class WalletDao {
    private String fromAddress = null;
    private String toAddress = null;
    private String startingBlock = null;
    private String endingBlock = null;
    private String balance = null;
    private ArrayList<String> tokenBalances = null;


    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getStartingBlock() {
        return startingBlock;
    }

    public void setStartingBlock(String startingBlock) {
        this.startingBlock = startingBlock;
    }

    public String getEndingBlock() {
        return endingBlock;
    }

    public void setEndingBlock(String endingBlock) {
        this.endingBlock = endingBlock;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public ArrayList<String> getTokenBalances() {
        return tokenBalances;
    }

    public void setTokenBalances(ArrayList<String> tokenBalances) {
        this.tokenBalances = tokenBalances;
    }

}
