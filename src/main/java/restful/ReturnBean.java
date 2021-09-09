package restful;
/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
public class ReturnBean {

    private String fileB64;
    private String sha1;

    public ReturnBean() {
    }

 
    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }


    public String getFileB64() {
        return fileB64;
    }


    public void setFileB64(String fileB64) {
        this.fileB64 = fileB64;
    }

   
}
