package com.github.monitorgroup.spring.boot;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.github.monitorgroup.bean.enums.MonitorEnum;

/**
 * Orgalon properties
 *
 * @author xionghui
 * @author niujunlong
 * @version 1.0.0
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "spring.orgalon")
public class OrgalonProperties {
  private Map<String, Monitor> monitors = new LinkedHashMap<String, Monitor>();

  public Map<String, Monitor> getMonitors() {
    return this.monitors;
  }

  public void setMonitors(Map<String, Monitor> monitors) {
    this.monitors = monitors;
  }

  @Override
  public String toString() {
    return "OrgalonProperties [monitors=" + this.monitors + "]";
  }

  /**
   * Monitor
   *
   * @author xionghui
   * @author niujunlong
   * @version 1.0.0
   * @since 1.0.0
   */
  public static class Monitor {
    private MonitorEnum monitor;
    private long initialDelay;
    private long delay = 1000;

    public MonitorEnum getMonitor() {
      return this.monitor;
    }

    public void setMonitor(MonitorEnum monitor) {
      this.monitor = monitor;
    }

    public long getInitialDelay() {
      return this.initialDelay;
    }

    public void setInitialDelay(long initialDelay) {
      this.initialDelay = initialDelay;
    }

    public long getDelay() {
      return this.delay;
    }

    public void setDelay(long delay) {
      this.delay = delay;
    }

    @Override
    public String toString() {
      return "Monitor [monitor=" + this.monitor + ", initialDelay=" + this.initialDelay + ", delay="
          + this.delay + "]";
    }
  }
}
