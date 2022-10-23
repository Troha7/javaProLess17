package ua.hillelit.lms;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ua.hillelit.lms.exceptions.DirectoryExistsException;

public class FileNavigator {

  private final String ROOT_DIRECTORY = "resources";
  private final Map<String, List<FileData>> fileMap = new HashMap<>();

  public void add(FileData file) {
    try {
      isRootDirectory(file);
      fileMap.putIfAbsent(file.getPath(), new ArrayList<>());
      fileMap.get(file.getPath()).add(file);
    } catch (DirectoryExistsException e) {
      e.printStackTrace();
    }
  }

  private void isRootDirectory(FileData file) throws DirectoryExistsException {
    File directory = new File(file.getPath());
    if (!directory.exists()) {
      String massage = "\nAdded new file directory [" + file.getPath() + "]"
          + " is not root directory! [" + ROOT_DIRECTORY + "]";
      throw new DirectoryExistsException(massage);
    }
  }

  public List<FileData> find(String path) {
    return fileMap.get(path);
  }

  public List<FileData> findAll() {
    List<FileData> files = new ArrayList<>();

    for (List<FileData> list : fileMap.values()) {
      files.addAll(list);
    }
    return files;
  }

  public List<FileData> filterBySize(Long size) {
    List<FileData> files = new ArrayList<>();

    for (FileData file : findAll()) {
      if (file.getSize() <= size) {
        files.add(file);
      }
    }
    return files;
  }

  public String remove(String path) {
    fileMap.remove(path);
    return path;
  }

  public List<FileData> sortBySize() {
    List<FileData> fileDataList = findAll();
    Collections.sort(fileDataList);

    return fileDataList;
  }

}
