package com.thinkgem.jeesite.common.logrecord;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.ele.entity.BizBasicShareinfo;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Description </br>
 * @Version Ver 1.0
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/2/9
 */
public class LogRecordUtil {

    public static void main(String[] args) {
        BizBasicShareinfo s1 = new BizBasicShareinfo();
        s1.setRohistnum("111");
        BizBasicShareinfo s2 = new BizBasicShareinfo();
        s2.setRohistnum("222");
        saveUpdateLog(s1, s2);
    }

    public static String saveUpdateLog(Object oldObj, Object newObj) {
        String result = new String();
        if (null != oldObj && null != newObj) {
            // 比较s1和s2不同的属性值，其中id忽略比较
            Map<String, List<Object>> compareResult = compareFields(oldObj, newObj,
                    new String[]{"id", "currentUser", "page", "sqlMap", "isNewRecord","createBy","createDate","updateBy","updateDate"});
            result = "本次操作共有" + compareResult.size() + "处修改，";
            Set<String> keySet = compareResult.keySet();
            for (String key : keySet) {
                List<Object> list = compareResult.get(key);
                result += key + "从" + "'" + list.get(0) + "'" + "修改为" + "'" + list.get(1) + "'！";
            }
        }
        return result;
    }

    /**
     * 比较两个实体属性值，返回一个map以有差异的属性名为key，value为一个list分别存obj1,obj2此属性名的值
     *
     * @param obj1      进行属性比较的对象1
     * @param obj2      进行属性比较的对象2
     * @param ignoreArr 选择忽略比较的属性数组
     * @return 属性差异比较结果map
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, List<Object>> compareFields(Object obj1, Object obj2, String[] ignoreArr) {
        try {
            Map<String, List<Object>> map = new HashMap<String, List<Object>>();
            List<String> ignoreList = null;
            if (ignoreArr != null && ignoreArr.length > 0) {
                // array转化为list
                ignoreList = Arrays.asList(ignoreArr);
            }
            if (obj1.getClass() == obj2.getClass()) {// 只有两个对象都是同一类型的才有可比性
                Class clazz = obj1.getClass();
                // 获取object的属性描述
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz,
                        Object.class).getPropertyDescriptors();
                for (PropertyDescriptor pd : pds) {// 这里就是所有的属性了
                    String name = pd.getName();// 属性名
                    if (ignoreList != null && ignoreList.contains(name)) {// 如果当前属性选择忽略比较，跳到下一次循环
                        continue;
                    }
                    Method readMethod = pd.getReadMethod();// get方法
                    // 在obj1上调用get方法等同于获得obj1的属性值
                    Object o1 = readMethod.invoke(obj1);
                    // 在obj2上调用get方法等同于获得obj2的属性值
                    Object o2 = readMethod.invoke(obj2);
                    if (o1 instanceof Timestamp) {
                        o1 = new Date(((Timestamp) o1).getTime());
                    }
                    if (o2 instanceof Timestamp) {
                        o2 = new Date(((Timestamp) o2).getTime());
                    }
                    if (o1 == null && o2 == null) {
                        continue;
                    } else if (o1 == null && o2 != null) {
                        List<Object> list = new ArrayList<Object>();
                        list.add(o1);
                        list.add(o2);
                        map.put(name, list);
                        continue;
                    }
                    if (!o1.equals(o2)) {// 比较这两个值是否相等,不等就可以放入map了
                        ExcelField foo = readMethod.getAnnotation(ExcelField.class);
                        if (null != foo) {
                            name = foo.title();
                        }
                        List<Object> list = new ArrayList<Object>();
                        list.add(o1);
                        list.add(o2);
                        map.put(name, list);
                    }
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
