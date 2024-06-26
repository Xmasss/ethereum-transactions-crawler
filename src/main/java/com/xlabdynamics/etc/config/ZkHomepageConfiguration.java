package com.xlabdynamics.etc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.zkoss.zkspringboot.ZkProperties;

@Configuration
@Controller
@ConditionalOnProperty(prefix="zk", name="homepage")
@EnableConfigurationProperties({ZkProperties.class})
public class ZkHomepageConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(org.zkoss.zkspringboot.ZkHomepageConfiguration.class);

    private final String homepage;

    public ZkHomepageConfiguration(ZkProperties zkProperties) {
        homepage = zkProperties.getHomepage();
        logger.info("ZK-Springboot: ZkHomepageConfiguration enabled - mapping the root path '/' to '{}'", homepage);
    }

}
