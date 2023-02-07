package com.zzt.rvadapter;

/**
 * 数据类不需要实现 {@link com.chad.library.adapter.base.entity.MultiItemEntity} 接口
 */
public class DelegateMultiEntity {
    public static final int TEXT = 1;
    public static final int IMG = 2;
    public static final int IMG_TEXT = 3;

    private String name;
    private int age;
    private String editTest ;

    @Override
    public String toString() {
        return "DelegateMultiEntity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", editTest='" + editTest + '\'' +
                '}';
    }

    public String getEditTest() {
        return editTest;
    }

    public void setEditTest(String editTest) {
        this.editTest = editTest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
