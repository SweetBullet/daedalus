package com.bullet.sweet.daedalus.kern;

import lombok.Data;

/**
 * Created by zhanlan on 16/11/23.
 */
@Data
public class HealthChecker {

    private volatile boolean isServerd;

    public void online() {
        this.isServerd = true;
    }

    public void offline() {
        this.isServerd = false;
    }

}
