package com.csu.mvc.wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class ByteArrayOutputStreamResponse extends HttpServletResponseWrapper {

    private ServletOutputStreamAdapter bos;

    public ByteArrayOutputStreamResponse(HttpServletResponse response) throws IOException {
        super(response);
        bos = new ServletOutputStreamAdapter(new ByteArrayOutputStream(1024));
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return bos;
    }


    public ByteArrayOutputStream getRawOutputStream() {
        return (ByteArrayOutputStream) bos.getDelegate();
    }

    public String getResponseContent() throws UnsupportedEncodingException {
        return new String(this.getRawOutputStream().toByteArray(), "UTF-8");
    }


    private class ServletOutputStreamAdapter extends ServletOutputStream {

        private OutputStream delegate;


        public ServletOutputStreamAdapter(OutputStream delegate) {
            this.delegate = delegate;
        }

        @Override
        public void write(int b) throws IOException {
            delegate.write(b);
        }

        @Override
        public void flush() throws IOException {
            delegate.flush();
        }

        @Override
        public void print(String s) throws IOException {
            delegate.write(s.getBytes());
        }

        @Override
        public void write(byte[] b) throws IOException {
            delegate.write(b);
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            delegate.write(b, off, len);
        }

        @Override
        public void close() throws IOException {
            delegate.close();
        }

        public OutputStream getDelegate() {
            return delegate;
        }
    }
}