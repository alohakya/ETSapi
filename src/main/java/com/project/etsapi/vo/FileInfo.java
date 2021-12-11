package com.project.etsapi.vo;

public class FileInfo {
    private String file_name;
    private String submit_time;
    private String file_size;

    public String getFile_name() {
        return file_name;
    }

    public String getSubmit_time() {
        return submit_time;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public void setSubmit_time(String submit_time) {
        this.submit_time = submit_time;
    }

    public String getFile_size() {
        return file_size;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }
}
