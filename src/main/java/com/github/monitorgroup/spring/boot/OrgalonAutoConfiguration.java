package com.github.monitorgroup.spring.boot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.github.monitorgroup.bean.MonitorBean;
import com.github.monitorgroup.service.OrgalonService;
import com.github.monitorgroup.service.ResultCallback;
import com.github.monitorgroup.service.impl.OrgalonServiceImpl;
import com.github.monitorgroup.spring.boot.OrgalonProperties.Monitor;

/**
 * Orgalon configuration
 *
 * @author xionghui
 * @author niujunlong
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
@ConditionalOnBean(annotation = EnableOrgalonConfiguration.class)
@EnableConfigurationProperties(OrgalonProperties.class)
public class OrgalonAutoConfiguration {

  @Autowired
  private OrgalonProperties properties;
  @Autowired
  private ApplicationContext applicationContext;

  private OrgalonService orgalonService;

  @PostConstruct
  public void start() {
    Map<String, Monitor> monitors = this.properties.getMonitors();
    if (monitors == null || monitors.isEmpty()) {
      return;
    }
    Map<String, ResultCallback> beanMap =
        this.applicationContext.getBeansOfType(ResultCallback.class);
    List<MonitorBean> monitorBeanList = new ArrayList<MonitorBean>();
    for (Map.Entry<String, Monitor> entry : monitors.entrySet()) {
      MonitorBean monitorBean = new MonitorBean();
      monitorBeanList.add(monitorBean);

      Monitor monitor = entry.getValue();
      monitorBean.setMonitor(monitor.getMonitor());
      monitorBean.setInitialDelay(monitor.getInitialDelay());
      monitorBean.setDelay(monitor.getDelay());
      ResultCallback resultCallback = beanMap == null ? null : beanMap.get(entry.getKey());
      monitorBean.setResultCallback(resultCallback);
    }
    this.orgalonService = new OrgalonServiceImpl();
    this.orgalonService.start(monitorBeanList);
  }


  public void stop() {
    if (this.orgalonService != null) {
      this.orgalonService.stop();
    }
  }
}
