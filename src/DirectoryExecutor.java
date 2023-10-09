import java.util.ArrayList;
import java.util.List;

//invoker
public class DirectoryExecutor {
    private final List<DirectoryOperations> DirectoryOperationList = new ArrayList<>();

    public Object executeOperation(DirectoryOperations directoryOperation){
        DirectoryOperationList.add(directoryOperation);
        return directoryOperation.execute();
    }
}
