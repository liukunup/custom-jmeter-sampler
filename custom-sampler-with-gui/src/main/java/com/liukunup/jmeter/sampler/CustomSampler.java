package com.liukunup.jmeter.sampler;

import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Liu Kun
 * @brief  Simple Sampler
 * @date 2022-08-06 13:53:51
 **/
public class CustomSampler extends AbstractSampler {

    private static final Logger logger = LoggerFactory.getLogger(CustomSampler.class);

    public CustomSampler() {
        // 初始化
        logger.info("public CustomSampler()");
    }

    @Override
    public SampleResult sample(Entry entry) {
        // 执行测试
        logger.info("public SampleResult sample(Entry entry)");
        // 核心代码逻辑
        SampleResult result = new SampleResult();
        // 设置采样器标签
        result.setSampleLabel(CustomSampler.class.getSimpleName());
        try {
            // 记录采样开始时间
            result.sampleStart();

            // 在这里编写你的测试逻辑
            // TODO
            // 例如: 执行一段耗时200毫秒的代码
            Thread.sleep(200);

            // 设置测试结果
            // 注意可以用`setResponseCodeOK()`代替`setResponseCode("200")`
            result.setResponseCodeOK();
            result.setSuccessful(true);
            result.setResponseData("setResponseData(this, utf-8)", "UTF-8");
            result.setResponseMessage("setResponseMessage()");
        } catch (Exception e) {
            // 当发生异常时,作如下返回
            result.setResponseMessage(e.toString());
            result.setResponseCode("500");
            result.setSuccessful(false);
        } finally {
            // 记录采样结束时间
            result.sampleEnd();
        }
        return result;
    }
}
