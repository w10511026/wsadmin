package com.thinkgem.jeesite.modules.ele.util;

import com.thinkgem.jeesite.modules.ele.entity.BizDirectPayinfo;
import com.thinkgem.jeesite.modules.ele.entity.BizSiteBaseinfo;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class InitImportData {

    public static Object getImportData(Object model) {
        List<Class<?>> classList = getClasses("com.thinkgem.jeesite.modules.ele.entity");
        for (Class<?> aClass : classList) {
            if (aClass == model.getClass()) {
                Field[] field = model.getClass().getDeclaredFields();
                try {
                    for (int j = 0; j < field.length; j++) { // 遍历所有属性
                        String name = field[j].getName(); // 获取属性的名字
                        name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
                        String type = field[j].getGenericType().toString(); // 获取属性的类型
                        if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                            Method m = model.getClass().getMethod("get" + name);
                            String value = (String) m.invoke(model); // 调用getter方法获取属性值
                            if (value == null) {
                                m = model.getClass().getMethod("set" + name, String.class);
                                m.invoke(model, "字符串属性");
                            }
                        }
                        if (type.equals("class java.lang.Integer")) {
                            Method m = model.getClass().getMethod("get" + name);
                            Integer value = (Integer) m.invoke(model);
                            if (value == null) {
                                m = model.getClass().getMethod("set" + name, Integer.class);
                                m.invoke(model, 1024);
                            }
                        }
                        if (type.equals("class java.lang.Boolean")) {
                            Method m = model.getClass().getMethod("get" + name);
                            Boolean value = (Boolean) m.invoke(model);
                            if (value == null) {
                                m = model.getClass().getMethod("set" + name, Boolean.class);
                                m.invoke(model, false);
                            }
                        }
                        if (type.equals("class java.util.Date")) {
                            Method m = model.getClass().getMethod("get" + name);
                            Date value = (Date) m.invoke(model);
                            if (value == null) {
                                m = model.getClass().getMethod("set" + name, Date.class);
                                m.invoke(model, new Date());
                            }
                        }
                        Field f = field[j];
                        f.setAccessible(true);
                        System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(model));
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        BizSiteBaseinfo baseinfo = new BizSiteBaseinfo();
        baseinfo.setSicmq(new Date());
        baseinfo.setSictq(new Date());
        baseinfo.setSicuq(new Date());
        baseinfo.setSidistrict("2121");
        baseinfo.setSipropertyunit("646");
        baseinfo.setSiretain("65");
        baseinfo.setSiroomstyle("llla");
        baseinfo.setSisitename("kkkkk");
        baseinfo.setSisitenum("tttttttt");
        return baseinfo;
    }

    public static void main(String[] args) {
        getImportData(new BizDirectPayinfo());
    }

    /**
     * 从包package中获取所有的Class
     *
     * @param packageName
     * @return
     */
    public static List<Class<?>> getClasses(String packageName) {

        //第一个class类的集合
        List<Class<?>> classes = new ArrayList<Class<?>>();
        //是否循环迭代
        boolean recursive = true;
        //获取包的名字 并进行替换
        String packageDirName = packageName.replace('.', '/');
        //定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            //循环迭代下去
            while (dirs.hasMoreElements()) {
                //获取下一个元素
                URL url = dirs.nextElement();
                //得到协议的名称
                String protocol = url.getProtocol();
                //如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    //获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    //以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                } else if ("jar".equals(protocol)) {
                    //如果是jar包文件
                    //定义一个JarFile
                    JarFile jar;
                    try {
                        //获取jar
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        //从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        //同样的进行循环迭代
                        while (entries.hasMoreElements()) {
                            //获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            //如果是以/开头的
                            if (name.charAt(0) == '/') {
                                //获取后面的字符串
                                name = name.substring(1);
                            }
                            //如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                //如果以"/"结尾 是一个包
                                if (idx != -1) {
                                    //获取包名 把"/"替换成"."
                                    packageName = name.substring(0, idx).replace('/', '.');
                                }
                                //如果可以迭代下去 并且是一个包
                                if ((idx != -1) || recursive) {
                                    //如果是一个.class文件 而且不是目录
                                    if (name.endsWith(".class") && !entry.isDirectory()) {
                                        //去掉后面的".class" 获取真正的类名
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                                        try {
                                            //添加到classes
                                            classes.add(Class.forName(packageName + '.' + className));
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    /**
     * 以文件的形式来获取包下的所有Class
     *
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes) {
        //获取此包的目录 建立一个File
        File dir = new File(packagePath);
        //如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        //如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            //自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            public boolean accept(File file) {
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        //循环所有文件
        for (File file : dirfiles) {
            //如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(),
                        file.getAbsolutePath(),
                        recursive,
                        classes);
            } else {
                //如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    //添加到集合中去
                    classes.add(Class.forName(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
