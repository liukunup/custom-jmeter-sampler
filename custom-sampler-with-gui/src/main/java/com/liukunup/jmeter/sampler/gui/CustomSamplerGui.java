package com.liukunup.jmeter.sampler.gui;

import com.liukunup.jmeter.sampler.CustomSampler;
import org.apache.jmeter.gui.util.HorizontalPanel;
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

    private JTextField param1;
    private JTextField param2;

    public CustomSamplerGui() {
        super();

        // 初始化
        logger.info("public CustomSamplerGui()");

        setLayout(new BorderLayout());
        setBorder(makeBorder());

        JPanel mainPanel = new VerticalPanel();
        add(mainPanel, BorderLayout.CENTER);

        // 参数1 加上标签
        param1 = new JTextField();
        JLabel label1 = new JLabel("Param1: ");
        label1.setLabelFor(param1);
        JPanel panel1 = new HorizontalPanel();
        panel1.add(label1, BorderLayout.WEST);
        panel1.add(param1, BorderLayout.CENTER);
        mainPanel.add(panel1);
        // 参数2
        param2 = new JTextField();
        JLabel label2 = new JLabel("Param1: ");
        label2.setLabelFor(param2);
        JPanel panel2 = new HorizontalPanel();
        panel2.add(label2, BorderLayout.WEST);
        panel2.add(param2, BorderLayout.CENTER);
        mainPanel.add(panel2);
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
