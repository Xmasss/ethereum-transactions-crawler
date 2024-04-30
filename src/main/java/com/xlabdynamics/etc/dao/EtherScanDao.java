package com.xlabdynamics.etc.dao;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class EtherScanDao {

        @JsonProperty
        private String blockNumber;

        @JsonProperty
        private String timeStamp;

        @JsonProperty
        private String hash;

        @JsonProperty
        private String nonce;

        @JsonProperty
        private String blockHash;

        @JsonProperty
        private String transactionIndex;

        @JsonProperty
        private String from;

        @JsonProperty
        private String to;

        @JsonProperty
        private String value;

        @JsonProperty
        private String gas;

        @JsonProperty
        private String gasPrice;

        @JsonProperty
        private String isError;

        @JsonProperty
        private String txreceipt_status;

        @JsonProperty
        private String input;

        @JsonProperty
        private String contractAddress;

        @JsonProperty
        private String cumulativeGasUsed;

        @JsonProperty
        private String gasUsed;

        @JsonProperty
        private String confirmations;

        @JsonProperty
        private String methodId;

        @JsonProperty
        private String functionName;

        private Map<String, Object> optional = new HashMap<>();

        public EtherScanDao() {
        }

        @JsonAnySetter
        public void addOptional(String name, Object value) {
            optional.put(name, value);
        }

        @JsonAnyGetter
        public Object getOptional(String name) {
            return optional.get(name);
        }

        public String getFrom() {
                return this.from;
        }

        public String getTo() {
                return this.to;
        }

        public String getValue() {
                return this.value;
        }
}
