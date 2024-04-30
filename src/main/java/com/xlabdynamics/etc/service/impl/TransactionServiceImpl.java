package com.xlabdynamics.etc.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xlabdynamics.etc.dao.CategoryDao;
import com.xlabdynamics.etc.dao.EtherScanDao;
import com.xlabdynamics.etc.dao.WalletDao;
import com.xlabdynamics.etc.mapper.EtherScanMapper;
import com.xlabdynamics.etc.service.TransactionService;
import org.springframework.web.client.RestTemplate;
import java.util.*;

public class TransactionServiceImpl implements TransactionService {
    private List<WalletDao> walletList = new ArrayList<WalletDao>(10);
    private Map<String, List<WalletDao>> queryResults;
    private CategoryDao rootCategory;


    public TransactionServiceImpl() {
        Random random = new Random(0L);
        String[][] raw = {
                {"0x95222290dd7278aa3ddd389cc1e1d165cc4bafe5", "0xaafcaa03fed8189b4fd93f49e74320d18e359517", "0.10472221948454609"},
                {"0x9c0982f553e7a276f8de94f741ca25b788a62801", "0xaafcaa03fed8189b4fd93f49e74320d18e359517", "2.99e-06"},
                {"0x95222290dd7278aa3ddd389cc1e1d165cc4bafe5", "0xaafcaa03fed8189b4fd93f49e74320d18e359517", "0.04796910511157751"},
                {"0x4838b106fce9647bdf1e7877bf73ce8b0bad5f97", "0xaafcaa03fed8189b4fd93f49e74320d18e359517", "0.02595679641167807"},
                {"0x95222290dd7278aa3ddd389cc1e1d165cc4bafe5", "0xaafcaa03fed8189b4fd93f49e74320d18e359517", "0.025024007820046157"},
                {"0x4838b106fce9647bdf1e7877bf73ce8b0bad5f97", "0xaafcaa03fed8189b4fd93f49e74320d18e359517", "0.03142204485346886"},
                {"0x95222290dd7278aa3ddd389cc1e1d165cc4bafe5", "0xaafcaa03fed8189b4fd93f49e74320d18e359517", "0.0433893126583772"},
                {"0x1f9090aae28b8a3dceadf281b0f12828e676c326", "0xaafcaa03fed8189b4fd93f49e74320d18e359517", "0.09902803868600746"},
                {"0x95222290dd7278aa3ddd389cc1e1d165cc4bafe5", "0xaafcaa03fed8189b4fd93f49e74320d18e359517", "0.13175795825886788"},
                {"0x4838b106fce9647bdf1e7877bf73ce8b0bad5f97", "0xaafcaa03fed8189b4fd93f49e74320d18e359517", "0.01826854796589442"}
        };

        for(int indx = 0; indx < raw.length; ++indx) {
            WalletDao walletDao = new WalletDao();
            walletDao.setFromAddress(raw[indx][0]);
            walletDao.setToAddress(raw[indx][1]);
            walletDao.setBalance(raw[indx][2]);
            walletList.add(walletDao);
        }
        Collections.shuffle(walletList, random);

        // create categories and query results
        rootCategory = new CategoryDao("", "All cars");
        queryResults = new HashMap<String, List<WalletDao>>();
        for(WalletDao walletDao : walletList) {
            String fromAddress = "fromAddress = " + walletDao.getFromAddress();
            queryResults.put(fromAddress, new LinkedList<WalletDao>());

            String toAddress = "toAddress = " + walletDao.getFromAddress();
            queryResults.put(toAddress, new LinkedList<WalletDao>());

            String balance = "balance = " + walletDao.getFromAddress();
            queryResults.put(balance, new LinkedList<WalletDao>());
        }
    }

    public TransactionServiceImpl(String wallet_address, String starting_block) {
        final String etherscan_api_key = "TBPJTYX9X6XTURPYFJRWT8B8MM1WCSGB97";
        final String uri = "https://api.etherscan.io/api?module=account&action=txlist&address=" +
                "" + wallet_address + "&startblock=" +
                "" + starting_block + "&endblock=latest&sort=asc&apikey=" +
                "" + etherscan_api_key + "";

        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(uri, String.class);


        ObjectMapper objectMapper = new ObjectMapper();
        String[] aa = jsonResponse.split("\\{\"status\":\"1\",\"message\":\"OK\",\"result\":");
        String json = aa[1].replaceAll(".$", "");
        EtherScanDao[] data = null;
        try {
            data = objectMapper.readValue(json, EtherScanDao[].class);
        } catch (JsonProcessingException e) {
            System.out.println();
        }

        String[][] raw = new String[data.length][3];

        raw=EtherScanMapper.toTransactionList(data);

        for(int indx = 0; indx < raw.length; ++indx) {
            WalletDao walletDao = new WalletDao();
            walletDao.setFromAddress(raw[indx][0]);
            walletDao.setToAddress(raw[indx][1]);
            walletDao.setBalance(raw[indx][2]);
            walletList.add(walletDao);
        }

        rootCategory = new CategoryDao("", "All cars");
        queryResults = new HashMap<String, List<WalletDao>>();
        for(WalletDao walletDao : walletList) {
            String fromAddress = "fromAddress = " + walletDao.getFromAddress();
            queryResults.put(fromAddress, new LinkedList<WalletDao>());

            String toAddress = "toAddress = " + walletDao.getFromAddress();
            queryResults.put(toAddress, new LinkedList<WalletDao>());

            String balance = "balance = " + walletDao.getFromAddress();
            queryResults.put(balance, new LinkedList<WalletDao>());
        }

    }

    @Override
    public List<WalletDao> findAll() {
        return walletList;
    }

    @Override
    public void setWalletDao(WalletDao walletDao) {

    }

    @Override
    public WalletDao getWalletDao() {
        return null;
    }


    public CategoryDao getCategoriesRoot() {
        return rootCategory;
    }

    @Override
    public int countByFilter(String filter) {
        return queryByFilter(filter).size();
    }

    @Override
    public List<WalletDao> queryByFilter(String filter) {
        return (filter == null || filter.length() <= 0) ? findAll() : queryResults.get(filter);
    }

}
