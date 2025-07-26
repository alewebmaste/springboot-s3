package com.borba.storage.configuration;

public class SqsProperties {

    private String queueUrl;
    private String dlqUrl;
    private String region;

    public String getQueueUrl() {
        return queueUrl;
    }

    public void setQueueUrl(String queueUrl) {
        this.queueUrl = queueUrl;
    }

    public String getDlqUrl() {
        return dlqUrl;
    }

    public void setDlqUrl(String dlqUrl) {
        this.dlqUrl = dlqUrl;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
