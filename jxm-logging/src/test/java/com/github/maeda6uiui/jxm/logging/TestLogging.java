package com.github.maeda6uiui.jxm.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogging {
    private static final Logger logger = LoggerFactory.getLogger(TestLogging.class);

    public static void main(String[] args) {
        logger.info("Hello world!");
    }
}
