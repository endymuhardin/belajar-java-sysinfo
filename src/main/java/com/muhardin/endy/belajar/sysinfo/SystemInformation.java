package com.muhardin.endy.belajar.sysinfo;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemInformation {
    private final Logger logger = LoggerFactory.getLogger(SystemInformation.class);
    
    private String hostname;
    private String os;
    private Long memory;
    private Long storage;
    private Set<String> networkInterfaces = new HashSet<String>();

    public void readData(){
        try {
            os = System.getProperty("os.name");
            os += ":" + System.getProperty("os.version");
            os += ":" + System.getProperty("os.arch");
            
            OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            memory = osBean.getTotalPhysicalMemorySize();
            
            hostname = InetAddress.getLocalHost().getHostName();
            
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if(interfaces != null){
                while(interfaces.hasMoreElements()){
                    NetworkInterface ni = interfaces.nextElement();
                    byte[] mac = ni.getHardwareAddress();
                    if(mac != null){
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < mac.length; i++) {
                                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
                        }
                        networkInterfaces.add(ni.getDisplayName()+":"+sb.toString());
                    }
                }
            }
        } catch (UnknownHostException ex) {
            logger.debug("Unknown Host");
            logger.trace(ex.getMessage(), ex);
            hostname = null;
        } catch (SocketException ex) {
            logger.debug("SocketException");
            logger.trace(ex.getMessage(), ex);
        }
    }
    
    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Long getMemory() {
        return memory;
    }

    public void setMemory(Long memory) {
        this.memory = memory;
    }

    public Long getStorage() {
        return storage;
    }

    public void setStorage(Long storage) {
        this.storage = storage;
    }

    public Set<String> getNetworkInterfaces() {
        return networkInterfaces;
    }

    public void setNetworkInterfaces(Set<String> networkInterfaces) {
        this.networkInterfaces = networkInterfaces;
    }
    
    
}
