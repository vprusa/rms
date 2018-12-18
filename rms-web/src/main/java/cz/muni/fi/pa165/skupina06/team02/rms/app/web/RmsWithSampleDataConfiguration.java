package cz.muni.fi.pa165.skupina06.team02.rms.app.web;

import cz.muni.fi.pa165.skupina06.team02.rms.app.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.skupina06.team02.rms.app.web.samples.SampleDataLoadingFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.web.samples.SampleDataLoadingFacadeImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Takes ServiceConfiguration and adds the SampleDataLoadingFacade bean.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class RmsWithSampleDataConfiguration {

    final static Logger log = LoggerFactory.getLogger(RmsWithSampleDataConfiguration.class);

    @Autowired
    SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() throws IOException {
        log.debug("dataLoading()");
        sampleDataLoadingFacade.loadData();
    }
}