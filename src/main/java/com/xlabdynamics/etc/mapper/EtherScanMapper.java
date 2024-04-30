package com.xlabdynamics.etc.mapper;

import com.xlabdynamics.etc.dao.EtherScanDao;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Long.parseLong;

public class EtherScanMapper {
    public static String[][] toTransactionList(EtherScanDao[] EtherScanDao) {
        List<List<String>> listOfLists = new ArrayList<List<String>>();

        for (EtherScanDao etherScanItem : EtherScanDao) {
            List transRecord = new ArrayList();
            transRecord.add(etherScanItem.getFrom());
            transRecord.add(etherScanItem.getTo());
            try {
                if (StringUtils.isNotEmpty(etherScanItem.getValue())) {
                    Long a = parseLong(etherScanItem.getValue());
                    Double b = a / 1e18;

                    transRecord.add(b.toString());
                }
                listOfLists.add(transRecord);
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
        }

        return listOfLists.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
    }

}
