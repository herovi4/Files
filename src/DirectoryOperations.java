public interface DirectoryOperations<T> {
    T execute();
}
//command
class GetNameDirectory implements DirectoryOperations<String>
{
    private final WorkingDirectory dir;
    public GetNameDirectory(WorkingDirectory dir){
        this.dir = dir;
    }
    @Override
    public String execute(){
        return dir.getDirectoryName();
    }
}

class PrintDirectoryContents implements DirectoryOperations<Void> {
    private final WorkingDirectory dir;

    public PrintDirectoryContents(WorkingDirectory dir){
        this.dir = dir;
    }

    @Override
    public Void execute() {
        dir.printCurrentDirectoryContents();
        return null;
    }
}

class ParentDirectoryName implements DirectoryOperations<String>{
    private final WorkingDirectory dir;
    public ParentDirectoryName(WorkingDirectory dir){
        this.dir=dir;
    }
    @Override
    public String execute(){
        return dir.getParentDirectory();
    }
}
class GoToParentDirectory implements DirectoryOperations<Boolean>{
    private final WorkingDirectory dir;
    public GoToParentDirectory(WorkingDirectory dir) {
        this.dir=dir;
    }

    @Override
    public Boolean execute() {
        return dir.navigateToParentDirectory();
    }
}
class checkChildDirectoryExist implements DirectoryOperations<Boolean>{
    private final WorkingDirectory dir;
    private final String childDirectoryName;
    public checkChildDirectoryExist(WorkingDirectory dir,String childDirectoryName) {

        this.dir=dir;
        this.childDirectoryName= childDirectoryName;
    }

    @Override
    public Boolean execute() {
        return dir.checkChildDirectoryExists(childDirectoryName);
    }
}
class createNewDirectory implements DirectoryOperations<Boolean>{
    private final WorkingDirectory dir;
    private final String newDirectoryName;
    public createNewDirectory(WorkingDirectory dir,String newDirectoryName) {
        this.dir=dir;
        this.newDirectoryName=newDirectoryName;
    }

    @Override
    public Boolean execute() {
        return dir.createNewDirectory(newDirectoryName);
    }
}
class GoToChildDirectory implements DirectoryOperations<Boolean>{
    private final WorkingDirectory dir;
    private final String ChildDirectoryName;
    public GoToChildDirectory(WorkingDirectory dir,String ChildDirectoryName) {
        this.ChildDirectoryName= ChildDirectoryName;
        this.dir=dir;
    }

    @Override
    public Boolean execute() {
        return dir.navigateToChildDirectory(ChildDirectoryName);
    }
}
class DeleteSubdirectories implements DirectoryOperations<Void>{
    private final WorkingDirectory dir;
    public DeleteSubdirectories(WorkingDirectory dir) {
        this.dir=dir;
    }

    @Override
    public Void execute() {
        dir.deleteAllSubdirectories();
        return null;
    }
}
class ListFilesbyExtensions implements DirectoryOperations<Void>{
    private final WorkingDirectory dir;
    private final String extension;
    public ListFilesbyExtensions(WorkingDirectory dir,String extension) {
        this.dir=dir;
        this.extension = extension;
    }

    @Override
    public Void execute() {
        dir.listFilesByExtension(extension);
        return null;
    }
}
class ListAllSubdirectoriesCommand implements DirectoryOperations<Void>{
    private final WorkingDirectory dir;
    public ListAllSubdirectoriesCommand(WorkingDirectory dir){
        this.dir = dir;
    }
    @Override
    public Void execute(){
        dir.listAllSubdirectories();
        return null;
    }
}
class CheckNestedDirectoryExists implements DirectoryOperations<Boolean>{
    private final WorkingDirectory dir;
    private final String nestedDirectoryName;
    public CheckNestedDirectoryExists(WorkingDirectory dir,String nestedDirectoryName) {
        this.nestedDirectoryName=nestedDirectoryName;
        this.dir=dir;
    }

    @Override
    public Boolean execute() {
        return dir.checkNestedDirectoryExists(nestedDirectoryName);

    }
}
