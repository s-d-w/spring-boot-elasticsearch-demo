package com.esdemo.bootstrap;

import org.springframework.core.Ordered;

public interface Bootstrap extends Ordered {
    void bootstrap();
}

