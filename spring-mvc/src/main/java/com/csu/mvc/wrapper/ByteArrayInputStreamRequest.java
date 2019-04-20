package com.csu.mvc.wrapper;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ByteArrayInputStreamRequest extends HttpServletRequestWrapper {

    private ServletInputStreamAdapter bis;

    public ByteArrayInputStreamRequest(HttpServletRequest request) throws IOException {
        super(request);
        InputStreamReader reader = new InputStreamReader(request.getInputStream());
        byte[] buffer = IOUtils.toByteArray(reader, "UTF-8");
        this.bis = new ServletInputStreamAdapter(new ByteArrayInputStream(buffer));
        reader.close();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return this.bis;
    }

    public String getRequestContent() throws IOException {
        return IOUtils.toString(this.bis, "UTF-8");
    }

    @Override
    public String[] getParameterValues(String name) {
        return super.getParameterValues(name);
    }

    private class ServletInputStreamAdapter extends ServletInputStream {

        private InputStream delegate;

        public ServletInputStreamAdapter(InputStream delegate) {
            this.delegate = delegate;
        }

        @Override
        public int read() throws IOException {
            return delegate.read();
        }

        @Override
        public int read(byte[] b) throws IOException {
            return delegate.read(b);
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            return delegate.read(b, off, len);
        }

        @Override
        public synchronized void mark(int readlimit) {
            delegate.mark(readlimit);
        }

        @Override
        public synchronized void reset() throws IOException {
            delegate.reset();
        }

        @Override
        public boolean markSupported() {
            return delegate.markSupported();
        }

        @Override
        public void close() throws IOException {
            delegate.close();
        }

        public InputStream getDelegate() {
            return delegate;
        }
    }
}