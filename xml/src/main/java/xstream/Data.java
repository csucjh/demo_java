package xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("root")
public class Data<T extends Body> {

    @XStreamAlias("head")
    private Header header;

    @XStreamAlias("body")
    private T body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
