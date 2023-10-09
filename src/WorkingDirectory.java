import java.io.File;

//receiver
public class WorkingDirectory {
    private  static WorkingDirectory instance;
    private String directoryName;

    private WorkingDirectory(String directoryName){
        this.directoryName = directoryName;
    }
    public  static WorkingDirectory getInstance(String directoryName){
        if(instance == null){
            instance = new WorkingDirectory(directoryName);
        }
        return instance;
    }
    public String getDirectoryName(){
        return directoryName;
    }
    public void printCurrentDirectoryContents() {
        File currentDir = new File(directoryName);
        if (currentDir.exists() && currentDir.isDirectory()) {
            String[] contents = currentDir.list();
            if (contents != null) {
                for (String item : contents) {
                    System.out.println(item);
                }
            }
        }
    }

    public String getParentDirectory() {
        File currentDir = new File(directoryName);
        File parentDir = currentDir.getParentFile();
        if (parentDir != null) {
            return parentDir.getAbsolutePath();
        }
        return null;
    }

    public boolean navigateToParentDirectory() {
        File currentDir = new File(directoryName);
        File parentDir = currentDir.getParentFile();
        if (parentDir != null && parentDir.exists() && parentDir.isDirectory()) {
            directoryName = parentDir.getAbsolutePath();
            return true;
        }
        return false;
    }

    public boolean checkChildDirectoryExists(String childDirectoryName) {
        File childDir = new File(directoryName, childDirectoryName);
        return childDir.exists() && childDir.isDirectory();
    }

    public boolean createNewDirectory(String newDirectoryName) {
        File newDir = new File(directoryName, newDirectoryName);
        return newDir.mkdir();
    }

    public boolean navigateToChildDirectory(String childDirectoryName) {
        File childDir = new File(directoryName, childDirectoryName);
        if (childDir.exists() && childDir.isDirectory()) {
            directoryName = childDir.getAbsolutePath();
            return true;
        }
        return false;
    }

    public boolean deleteAllSubdirectories() {
        File currentDir = new File(directoryName);
        if (currentDir.exists() && currentDir.isDirectory()) {
            File[] subdirectories = currentDir.listFiles(File::isDirectory);
            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    deleteDirectory(subdirectory);
                }
            }
            return true;
        }
        return false;
    }

    private void deleteDirectory(File directory) {
        File[] contents = directory.listFiles();
        if (contents != null) {
            for (File file : contents) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }

    public void listFilesByExtension(String extension) {
        File currentDir = new File(directoryName);
        if (currentDir.exists() && currentDir.isDirectory()) {
            File[] files = currentDir.listFiles((dir, name) -> name.endsWith("." + extension));
            if (files != null) {
                for (File file : files) {
                    System.out.println(file.getName());
                }
            }
        }
    }

    public void listAllSubdirectories() {
        listAllSubdirectoriesRecursive(directoryName, 0);
    }

    private void listAllSubdirectoriesRecursive(String dirPath, int level) {
        File currentDir = new File(dirPath);
        if (currentDir.exists() && currentDir.isDirectory()) {
            File[] subdirectories = currentDir.listFiles(File::isDirectory);
            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    for (int i = 0; i < level; i++) {
                        System.out.print("  "); // Indentation for hierarchy
                    }
                    System.out.println(subdirectory.getName());
                    listAllSubdirectoriesRecursive(subdirectory.getAbsolutePath(), level + 1);
                }
            }
        }
    }

    public boolean checkNestedDirectoryExists(String nestedDirectoryName) {
        File currentDir = new File(directoryName);
        if (currentDir.exists() && currentDir.isDirectory()) {
                return checkNestedDirectoryRecursive(currentDir, nestedDirectoryName);
        }
        return false;
    }

    private boolean checkNestedDirectoryRecursive(File dir, String nestedDirectoryName) {
        File[] subdirectories = dir.listFiles(File::isDirectory);
        if (subdirectories != null) {
            for (File subdirectory : subdirectories) {
                if (subdirectory.getName().equals(nestedDirectoryName)) {
                    System.out.println(subdirectory);
                    return true;
                }
                if (checkNestedDirectoryRecursive(subdirectory, nestedDirectoryName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
