package com.xlabdynamics.etc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.zkoss.web.util.resource.ClassWebResource;
import org.zkoss.zkspringboot.ZkProperties;

@Configuration
@EnableConfigurationProperties({org.zkoss.zkspringboot.ZkProperties.class})
public class ZkWebMvcConfiguration implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(org.zkoss.zkspringboot.ZkWebMvcConfiguration.class);

    private final org.zkoss.zkspringboot.ZkProperties zkProperties;

    public ZkWebMvcConfiguration(ZkProperties zkProperties) {
        this.zkProperties = zkProperties;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        if(zkProperties.isZulViewResolverEnabled()) {
            String prefix;
            if(zkProperties.isWar()) {
                prefix = zkProperties.getZulViewResolverPrefix() + "/";
            } else {
                prefix = zkProperties.getUpdateUri() + ClassWebResource.PATH_PREFIX + zkProperties.getZulViewResolverPrefix() + "/";
            }
            final String suffix = zkProperties.getZulViewResolverSuffix();
            logger.info("ZK-Springboot: InternalViewResolver enabled - e.g. resolving view 'example' to '{}example{}'", prefix, suffix);
            InternalResourceViewResolver resolver = new InternalResourceViewResolver(prefix, suffix);
            resolver.setOrder(InternalResourceViewResolver.LOWEST_PRECEDENCE);
            registry.viewResolver(resolver);
        }
    }
}
