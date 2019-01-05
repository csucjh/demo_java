package try_resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * https://www.cnblogs.com/itZhy/p/7636615.html
 * <p>
 * try-with-resource并不是JVM虚拟机的新增功能，只是JDK实现了一个语法糖，当你将上面代码反编译后会发现，其实对JVM虚拟机而言，它看到的依然是之前的写法
 * <p>
 * <p>
 * 通过反编译的代码，大家可能注意到代码中有一处对异常的特殊处理：
 * var2.addSuppressed(var11);
 * 这是try-with-resource语法涉及的另外一个知识点，叫做异常抑制。当对外部资源进行处理（例如读或写）时，如果遭遇了异常，且在随后的关闭外部资源过程中，又遭遇了异常，
 * 那么你catch到的将会是对外部资源进行处理时遭遇的异常，关闭资源时遭遇的异常将被“抑制”但不是丢弃，通过异常的getSuppressed方法，可以提取出被抑制的异常。
 */
public class TestTryResource {

    public static void main(String[] args) {
        try (FileInputStream is = new FileInputStream(new File("distribute"))) {
            System.out.println(is.read());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
