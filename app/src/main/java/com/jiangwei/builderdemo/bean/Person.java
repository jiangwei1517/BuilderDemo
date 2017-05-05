package com.jiangwei.builderdemo.bean;

/**
 * author: jiangwei18 on 17/5/5 11:04 email: jiangwei18@baidu.com Hi: jwill金牛
 */

public class Person {
    private String name;
    private int age;
    private String nation;
    private String sex;

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + ", nation='" + nation + '\'' + ", sex='" + sex
                + '\'' + '}';
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    private Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.nation = builder.nation;
        this.sex = builder.sex;
    }

    public static class Builder {
        private String name;
        private int age;
        private String nation;
        private String sex;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder nation(String nation) {
            this.nation = nation;
            return this;
        }

        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }

        public Person build() {
            // Builder模式是非线程安全的，如果要在Builder内部类中检查一个参数的合法性，必需要在对象创建完成之后再检查，
            Person p = new Person(this);
            if (p.age > 200) {
                throw new IllegalStateException("age error" + p.age);
            }
            return new Person(this);
        }
    }
}
