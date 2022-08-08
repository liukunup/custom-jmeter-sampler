package com.liukunup.jmeter.sampler.gui;

import com.liukunup.jmeter.sampler.CustomSampler;
import org.apache.jmeter.gui.util.VerticalPanel;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * 自定义`JMeter`采样器的GUI界面
 * 继承自`AbstractSamplerGui`类
 * @author Liu Kun
 * @date 2022-08-08 23:29:33
 **/
public class CustomSamplerGui extends AbstractSamplerGui {

    private static final Logger logger = LoggerFactory.getLogger(CustomSamplerGui.class);

    private final JTextField param1 = new JTextField();
    private final JTextField param2 = new JTextField();

    public CustomSamplerGui() {
        // 初始化
        logger.info("public CustomSamplerGui()");

        setLayout(new BorderLayout());
        setBorder(makeBorder());

        JPanel mainPanel = new VerticalPanel();
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(param1);
        mainPanel.add(param2);
    }

    @Override
    public String getStaticLabel() {
        // 返回采样器的显示名称
        // 注意: 区分好 public String getLabelResource() 函数
        logger.info("public String getStaticLabel()");
        return "Custom Sampler";
    }

    @Override
    public String getLabelResource() {
        // 这一句永远不会被执行到
        logger.info("public String getLabelResource()");
        return null;
    }

    @Override
    public TestElement createTestElement() {
        // 创建一个新的Sampler并接收界面数据
        logger.info("public TestElement createTestElement()");

        CustomSampler sampler = new CustomSampler();
        this.configureTestElement(sampler);
        sampler.setParam1(Integer.parseInt(param1.getText()));
        sampler.setParam2(Integer.parseInt(param2.getText()));

        return sampler;
    }

    @Override
    public void modifyTestElement(TestElement testElement) {
        // 当界面数据发生变化时接收界面数据
        logger.info("public void modifyTestElement(TestElement testElement)");

        CustomSampler sampler = (CustomSampler) testElement;
        this.configureTestElement(sampler);
        sampler.setParam1(Integer.parseInt(param1.getText()));
        sampler.setParam2(Integer.parseInt(param2.getText()));
    }

    @Override
    public void clearGui() {
        super.clearGui();

        logger.info("public void clearGui()");

        this.param1.setText("5");
        this.param2.setText("6");
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);

        logger.info("public void configure(TestElement element)");

        CustomSampler sampler = (CustomSampler) element;
        this.param1.setText(String.valueOf(sampler.getParam1()));
        this.param2.setText(String.valueOf(sampler.getParam2()));
    }
}
