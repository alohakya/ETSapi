package com.project.etsapi.vo;

import java.util.List;

public class DirectoryFileInfo {
    private String folderName;
    private List<String> fileName;

    public List<String> getFileName() {
        return fileName;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFileName(List<String> fileName) {
        this.fileName = fileName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
