package com.bullet.sweet.daedalus.kern;

import lombok.Data;

/**
 * Created by zhanlan on 16/11/23.
 */
@Data
public class HealthChecker {

    private volatile boolean isServed;

    public void online() {
        this.isServed = true;
    }

    public void offline() {
        this.isServed = false;
    }

}
