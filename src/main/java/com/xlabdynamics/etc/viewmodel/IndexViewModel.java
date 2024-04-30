package com.xlabdynamics.etc.viewmodel;

import com.xlabdynamics.etc.form.IndexForm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexViewModel extends IndexForm {

    private String currentUrl;
    private Object data;

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private String foregroundColour = "#000000", backgroundColour = "#FDC966";
    private String startingBlock = "19553622";
    private String toAddress = "0xAAfCaA03fED8189b4Fd93F49E74320D18e359517";

    @Override
    public String getStartingBlock() { return startingBlock; }

    @Override
    public void setStartingBlock(String startingBlock) { this.startingBlock = startingBlock;}

    @Override
    public String getToAddress() { return toAddress; }

    @Override
    public void setToAddress(String toAddress) { this.toAddress = toAddress; }

    public String getForegroundColour() {
        return foregroundColour;
    }

    public void setForegroundColour(String foregroundColor) {
        this.foregroundColour = foregroundColor;
    }

    public String getBackgroundColour() {
        return backgroundColour;
    }

    public void setBackgroundColour(String backgroundColor) {
        this.backgroundColour = backgroundColor;
    }

    Map<String, PageModel<String>> pages = new HashMap<>();
    private PageModel<String> currentPage;

    @Wire("#toAddressIndex")
    private Textbox toAddressIndex;

    @Wire("#startingBlockIndex")
    private Textbox startingBlockIndex;

    @RequestMapping("/")
    public String index() {
        return "ROOT: Hello World SUPER TROOPER 4\n";
    }

    private IndexForm indexForm;
    @Init
    public void init(@ExecutionArgParam("data") Object data) {
        pages.put("page1", new PageModel<>("~./zul/transactionData.zul", ""+this.toAddress+"|"+this.startingBlock));
        pages.put("page2", new PageModel<>("~./zul/balanceAtDate.zul", "data for page 2"));
    }

    @AfterCompose
    public void setupComponents(@ContextParam(ContextType.VIEW) Component component,
                                @ExecutionArgParam("indexForm") IndexForm indexForm) {

        Selectors.wireComponents(component, this, false);
    }

    @Command
    @NotifyChange("currentPage")
    public void navigate(@BindingParam("page") String page) {
        this.currentPage = pages.get(page);
    }

    public PageModel getCurrentPage() {
        return currentPage;
    }

}

