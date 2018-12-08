package com.example.cbpierre.epromonitor;

import com.example.cbpierre.epromonitor.models.Login;

public interface AsyncResult {
    void asyncFinished(Login login);

    void asyncUserCount(Integer count);
}
