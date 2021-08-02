package com.utils;


public class Base64VO {
    private String name;
    private String base64;

    /* 14 */
    public String toString() {
        return "Base64VO{name='" + this.name + '\'' + ", base64='" + this.base64 + '\'' + '}';
    }


    /* 21 */
    public String getName() {
        return this.name;
    }


    /* 25 */
    public void setName(String name) {
        this.name = name;
    }


    /* 29 */
    public String getBase64() {
        return this.base64;
    }


    /* 33 */
    public void setBase64(String base64) {
        this.base64 = base64;
    }
}


/* Location:              /Users/ilove52345234/eclipse_TestWorkspace/Test/src/main/ImportedClasses/com/utils/Base64VO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */