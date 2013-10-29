/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.muhardin.endy.belajar.sysinfo;

import java.util.Set;
import org.junit.Test;


/**
 *
 * @author endy
 */
public class SystemInformationTest {
    
    @Test
    public void testReadData() {
        SystemInformation si = new SystemInformation();
        si.readData();
        
        System.out.println("OS : " +si.getOs());
        System.out.println("Memory : " +si.getMemory());
        System.out.println("Hostname : " +si.getHostname());
        
        Set<String> interfaces = si.getNetworkInterfaces();
        System.out.println("Number of interfaces : "+interfaces.size());
        for (String mac : interfaces) {
            System.out.println("MAC : "+mac);
        }
    }

    
}
