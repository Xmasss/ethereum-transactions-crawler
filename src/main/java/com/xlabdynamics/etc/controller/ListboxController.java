package com.xlabdynamics.etc.controller;

import java.math.BigDecimal;
import java.util.Set;
import com.xlabdynamics.etc.dao.WalletDao;
import com.xlabdynamics.etc.service.TransactionService;
import com.xlabdynamics.etc.service.impl.TransactionServiceImpl;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class ListboxController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 1L;

    private ListModel<WalletDao> walletDaoListModel;

    @Wire
    private Window win;

    public ListboxController() {
        TransactionService transactionService = new TransactionServiceImpl("0xAAfCaA03fED8189b4Fd93F49E74320D18e359517", "19553622");
        walletDaoListModel = new ListModelList<WalletDao>(transactionService.findAll());
        ((ListModelList<WalletDao>)walletDaoListModel).setMultiple(true);
    }

    public ListModel<WalletDao> getWalletDaoListModel() {
        return walletDaoListModel;
    }

    @Listen("onSelect = listbox")
    public void updateMessage() {
        BigDecimal sum=BigDecimal.valueOf(0);
        Set<WalletDao> selectedWalletDao = ((ListModelList<WalletDao>)walletDaoListModel).getSelection();
        int size = selectedWalletDao.size();
        showNotify(size > 0 ? size + " transactions selected." : "No transaction selected.", win);
    }

    private void showNotify(String msg,Component ref){
        Clients.showNotification(msg,"info",ref,"top_right",2000);
    }
}