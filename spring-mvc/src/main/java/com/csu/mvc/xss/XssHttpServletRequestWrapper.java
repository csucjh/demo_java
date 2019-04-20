package com.csu.mvc.xss;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    boolean isUpData = false;

    private static Pattern[] patterns = new Pattern[] {
            //Script fragments
            Pattern.compile("<scripts>(.*?)</scripts>",Pattern.CASE_INSENSITIVE),
            //src = "..."
            Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE),
            Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE),
            //lonely script tags
            Pattern.compile("</script>",Pattern.CASE_INSENSITIVE),
            Pattern.compile("<script(.*?)>",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            //eval(...)
            Pattern.compile("eval\\((.*?)\\)",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            //expression(...)
            Pattern.compile("expression\\((.*?)\\)",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            //javascript:...
            Pattern.compile("javascript:",Pattern.CASE_INSENSITIVE),
            //vbscript:...
            Pattern.compile("vbscript:",Pattern.CASE_INSENSITIVE),

            //onload(...)=...
            Pattern.compile("onload(.*?)=",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),

            Pattern.compile("onblur(.*?)=",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("onclick(.*?)=",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("onerror(.*?)=",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("onfocus(.*?)=",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("oninput(.*?)=",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("onmouseover(.*?)=",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("onscroll(.*?)=",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("autofocus(.*?)=",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),

    };

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        String contentType = request.getContentType ();
        if (null != contentType)
            isUpData =contentType.startsWith ("multipart");
    }

    /**
     * 规范化后请求参数map
     */
    private Map<String, String[]> sanitized;

    //getParameterValues()
    public String[] getParameterValues(String paramter){
        String[] values = super.getParameterValues(paramter);

        if(values == null) {
            return values;
        }

        int count = values.length;
        String[] encodeValues = new String[count];

        for(int i = 0; i < count; i++) {
            encodeValues[i] = xssEncode(stripXSS(values[i]));
        }

        return encodeValues;

    }

    //getParameter()
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);

        return xssEncode(stripXSS(value));
    }

    //getParameterMap()
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> maps = super.getParameterMap();

        if (sanitized==null)
            sanitized = sanitizeParamMap(maps);
        return sanitized;
    }

    /**
     * 规范请求参数
     * @param raw
     * @return
     */
    private  Map<String, String[]> sanitizeParamMap(Map<String, String[]> raw)
    {
        Map<String, String[]> res = new HashMap<String, String[]>();
        if (raw==null)
            return res;

        for (String key : (Set<String>) raw.keySet())
        {
            String[] rawVals = raw.get(key);
            String[] snzVals = new String[rawVals.length];
            for (int i=0; i < rawVals.length; i++)
            {
                snzVals[i] = xssEncode(stripXSS(rawVals[i]));
            }
            res.put(key, snzVals);
        }
        return res;
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (isUpData) {

            return super.getInputStream();

        } else {

            String charSet = Charset.defaultCharset().toString();

            //为空，直接返回
            String json = IOUtils.toString(super.getInputStream(), charSet);
            if (StringUtils.isBlank(json)) {
                return super.getInputStream();
            }

            //xss过滤
            json = xssEncode(json);

            final ByteArrayInputStream bais = new ByteArrayInputStream(json.getBytes(charSet));

            return new ServletInputStream() {

                @Override
                public int read() {
                    return bais.read();
                }

            };
        }
    }

    //getHeader()
    public String getHeader(String name) {
        String value = super.getHeader(name);

        return xssEncode(stripXSS(value));
    }

    /**
     *NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
     *avoid encoded attacks.
     *value = ESAPI.encoder().canonicalize(value);
     * */
    private static String stripXSS(String value) {

        if(value != null) {
            // Avoid null characters
            value = value.replaceAll("\0", "");

            for(Pattern scriptPattern : patterns) {
                value = scriptPattern.matcher(value).replaceAll("");
            }
        }
        return value;
    }

    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     *
     * @param s
     * @return
     */
    private static String xssEncode(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
//                    sb.append("&gt;");//全角大于号
                    sb.append("＞");//全角大于号
                    break;
                case '<':
//                    sb.append("&lt;");//全角小于号
                    sb.append("＜");//全角小于号
                    break;
                case '\'':
                    sb.append('‘');//全角单引号
                    break;
                     /*
                 case '\"':
                     sb.append('“');//全角双引号
                     break;*/
                 /*case '&':
                     sb.append('＆');//全角
                     break;*/
                case '\\':
                    sb.append('＼');//全角斜线
                    break;
                case '#':
                    sb.append('＃');//全角井号
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(XssHttpServletRequestWrapper.xssEncode(stripXSS("<script>alert(1)</script>")));
        System.out.println(XssHttpServletRequestWrapper.xssEncode(stripXSS("<svg onload=alert`2`>")));
    }
}