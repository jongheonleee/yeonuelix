package yeo.nuel.lix.authentication;

public class RequestedBy implements Authentication {

    private final String requestedBy;

    public RequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    @Override
    public String getRequestedBy() {
        return "";
    }
}
