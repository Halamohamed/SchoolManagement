package se.ecutbildning.Ronneby.exeptions;

public class ResourceExist extends Throwable {
    public ResourceExist() {
    }

    public ResourceExist(String message) {
        super(message);
    }
}
