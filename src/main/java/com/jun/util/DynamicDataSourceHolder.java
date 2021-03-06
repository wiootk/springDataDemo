package com.jun.util;

public  class DynamicDataSourceHolder {
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();
    public static void setDataSource(String name) {
        holder.set(name);
    }
    public static String getDataSouce() {
        return holder.get();
    }
    public static void  clearDataSouce() {
        holder.remove();
    }
}
