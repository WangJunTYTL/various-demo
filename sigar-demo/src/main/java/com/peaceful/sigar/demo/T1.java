package com.peaceful.sigar.demo;

import com.peaceful.common.util.Util;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;


/**
 * @author WangJun
 * @version 1.0 16/5/21
 */
public class T1 {


    public static void main(String[] args) throws Exception {
        String userDir = System.getProperty("user.dir");
        String lib_path = System.getProperty("java.library.path");
        Util.report("local->" + lib_path);

        /*System.setProperty("java.library.path", userDir + "/lib");
        String new_lib = System.getProperty("java.library.path");
        Sigar sigar = new Sigar();
        Util.report("local->" + new_lib);
        System.setProperty("java.library.path", lib_path);
*/
        addDir(userDir+"/lib");
        lib_path = System.getProperty("java.library.path");
        Util.report("local->" + lib_path);
        Sigar sigar = new Sigar();
        // CPU数量（单位：个）
        int cpuLength = sigar.getCpuInfoList().length;
        Util.report(cpuLength + "");

        // CPU的总量（单位：HZ）及CPU的相关信息
        CpuInfo infos[] = sigar.getCpuInfoList();
        for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
            CpuInfo info = infos[i];
            Util.report("mhz=" + info.getMhz());// CPU的总量MHz
            Util.report("vendor=" + info.getVendor());// 获得CPU的卖主，如：Intel
            Util.report("model=" + info.getModel());// 获得CPU的类别，如：Celeron
            Util.report("cache size=" + info.getCacheSize());// 缓冲存储器数量
        }

/** CPU的用户使用量、系统使用剩余量、总的剩余量、总的使用占用量等（单位：100%） **/
// 方式一，主要是针对一块CPU的情况
        CpuPerc cpu;
        try {
            cpu = sigar.getCpuPerc();
//            Util.reportCpuPerc(cpu);
        } catch (SigarException e) {
            e.printStackTrace();
        }

// 方式二，不管是单块CPU还是多CPU都适用
        CpuPerc cpuList[] = null;
        try {
            cpuList = sigar.getCpuPercList();
        } catch (SigarException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < cpuList.length; i++) {
            // Util.reportCpuPerc(cpuList[i]);
        }


    }

    public static void addDir(String s) throws IOException {
        try {
            // This enables the java.library.path to be modified at runtime
            // From a Sun engineer at http://forums.sun.com/thread.jspa?threadID=707176
            //
            Field field = ClassLoader.class.getDeclaredField("usr_paths");
            field.setAccessible(true);
            String[] paths = (String[])field.get(null);
            for (int i = 0; i < paths.length; i++) {
                if (s.equals(paths[i])) {
                    return;
                }
            }
            String[] tmp = new String[paths.length+1];
            System.arraycopy(paths,0,tmp,0,paths.length);
            tmp[paths.length] = s;
            field.set(null,tmp);
            System.setProperty("java.library.path", System.getProperty("java.library.path") + File.pathSeparator + s);
        } catch (IllegalAccessException e) {
            throw new IOException("Failed to get permissions to set library path");
        } catch (NoSuchFieldException e) {
            throw new IOException("Failed to get field handle to set library path");
        }
    }
}
