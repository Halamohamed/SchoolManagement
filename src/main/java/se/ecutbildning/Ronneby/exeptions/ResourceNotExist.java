package se.ecutbildning.Ronneby.exeptions;

public class ResourceNotExist extends Throwable {
    public ResourceNotExist(String s) {
        super(s);
    }

    public ResourceNotExist() {
        super();
    }

}
