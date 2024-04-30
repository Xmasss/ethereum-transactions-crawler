package com.xlabdynamics.etc.service;

import com.xlabdynamics.etc.dao.CategoryDao;
import com.xlabdynamics.etc.dao.WalletDao;

import java.util.List;


public interface TransactionService {

    public List<WalletDao> findAll();

    void setWalletDao(WalletDao walletDao);

    WalletDao getWalletDao();

    CategoryDao getCategoriesRoot();

    int countByFilter(String filter);

    List<WalletDao> queryByFilter(String filter);
}