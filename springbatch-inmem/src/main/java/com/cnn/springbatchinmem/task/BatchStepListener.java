package com.cnn.springbatchinmem.task;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.annotation.OnReadError;

/**
 * @author stxyg
 * @date 2021/5/11 14:54
 */
@Slf4j
public class BatchStepListener<T, S> {

    @OnReadError
    public static void onReadError(Exception e) {
        log.error("批处理-读异常，e={}", ExceptionUtils.getStackTrace(e));
    }

    @BeforeWrite
    public void beforeWrite(List<S> items) {
        log.info("批处理-写操作开始");
    }

    @AfterWrite
    public void afterWrite(List<S> items) {
        log.info("批处理-写操作结束");
    }

}
