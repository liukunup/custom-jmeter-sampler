package com.liukunup.jmeter.sampler;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义`JMeter`采样器
 * 继承自`AbstractJavaSamplerClient`类
 * @author Liu Kun
 * @date   2022-08-07 14:06:00
 **/
public class CustomJavaSamplerClient extends AbstractJavaSamplerClient {

    private static final Logger logger = LoggerFactory.getLogger(CustomJavaSamplerClient.class);

    public CustomJavaSamplerClient() {
        // 初始化
        logger.info("public CustomJavaSamplerClient()");
    }

    @Override
    public void setupTest(JavaSamplerContext context) {
        super.setupTest(context);

        // 每次执行`runTest`前运行一次
        logger.info("public void setupTest(JavaSamplerContext context)");
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        // 执行测试
        logger.info("public SampleResult runTest(JavaSamplerContext javaSamplerContext)");
        // 核心代码逻辑
        SampleResult result = new SampleResult();
        // 设置采样器标签
        result.setSampleLabel(CustomJavaSamplerClient.class.getSimpleName());
        try {
            // 记录采样开始时间
            result.sampleStart();

            // 在这里编写你的测试逻辑
            // TODO
            // 例如: 执行一段耗时200毫秒的代码
            Thread.sleep(200);

            // 样例: 实现一个简单的加法来验证传参
            int a = javaSamplerContext.getIntParameter("默认参数1");
            int b = javaSamplerContext.getIntParameter("默认参数2");
            int c = a + b;
            logger.info("计算: " + a + " + " + b + " = " + c);

            // 设置测试结果
            // 注意可以用`setResponseCodeOK()`代替`setResponseCode("200")`
            result.setResponseCodeOK();
            result.setSuccessful(true);
            result.setResponseData("setResponseData(this, utf-8), c= " + c, "UTF-8");
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

    @Override
    public void teardownTest(JavaSamplerContext context) {
        // 每次执行`runTest`后运行一次
        logger.info("public void teardownTest(JavaSamplerContext context)");

        super.teardownTest(context);
    }

    @Override
    public Arguments getDefaultParameters() {
        // 返回界面上的默认参数及其值
        logger.info("public Arguments getDefaultParameters()");
        // 构建默认参数
        Arguments arguments = new Arguments();
        arguments.addArgument("默认参数1", "3");
        arguments.addArgument("默认参数2", "4");
        return arguments;
    }

}
