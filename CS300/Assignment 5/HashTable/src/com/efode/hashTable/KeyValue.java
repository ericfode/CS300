//pair taken from: http://stackoverflow.com/questions/156275/what-is-the-equivalent-of-the-c-pairl-r-in-java
//author: arturh
//And modified by: Eric Fode (ericfode@gmail.com)
//Modifications: changed to key value pair that takes strings only and uses my string hasher
//A is Key, B is Value
package com.efode.hashTable;

public class KeyValue {
    private String key;
    private String value;

    public KeyValue(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    public long keyHashCode() {
        return StringHasher.hash(key);
    }

    public boolean equals(KeyValue p) {
    	return p.value == this.value;
    }

    public String toString()
    { 
           return "(" + key + ", " + value + ")"; 
    }

    public String getKey() {
        return key;
    }

    public void setFirst(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}