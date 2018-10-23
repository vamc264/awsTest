package com.karmarama.qa.core;

import com.saucelabs.saucerest.SauceREST;
import org.junit.rules.TestWatcher;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.karmarama.qa.core.Properties.getSauceAccessKey;
import static com.karmarama.qa.core.Properties.getSauceUsername;
import static net.serenitybdd.core.Serenity.*;

public class SauceLabsWatcher extends TestWatcher {

    static String username, accessKey;
    private SauceREST sauceREST;
    private Map<String, Object> job = new HashMap();

    SauceLabsWatcher() {
        username = getSauceUsername();
        accessKey = getSauceAccessKey();

        setSauceREST();
    }

    void setJobStatus(boolean passed) {
        job.put("passed", passed);
        updateJobInfo(job);
    }

    void setJobTags(Collection<String> tags) {
        job.put("tags", tags);
        updateJobInfo(job);
    }

    private void updateJobInfo(Map<String, Object> job) {
        sauceREST.updateJobInfo(getCurrentSessionID(), job);
    }

    private void setSauceREST() {
        if (sauceREST == null)
            sauceREST = new SauceREST(username, accessKey);
    }
}
