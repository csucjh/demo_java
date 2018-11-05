package string;

public class StringBuilderRemove {

    public long newProject;//重新创建对象循环一千万次所用时间
    public long delete;//删除字符串循环一千万次所用时间
    public long updateLength;//修改长度循环一千万次所用时间

    public void test() {

        //第一种,新创建一个对象
        long startTimeA = System.currentTimeMillis();
        StringBuilder sb = null;
        for (int i = 1; i <= 100000; i++) {
            sb = new StringBuilder();
            sb.append("AAAAAAA");
            sb.append("BBBBBBB");
            sb.append("CCCCCCC");
            sb.append("DDDDDDD");
            sb.append("EEEEEEE");
            sb.append("FFFFFFF");
            sb.append("GGGGGGG");
            String a = sb.toString();
        }
        this.newProject = System.currentTimeMillis() - startTimeA;//new新对象所花时间
        //第二种,删除字符串
        long startTimeB = System.currentTimeMillis();

        for (int i = 1; i <= 10000000; i++) {
            sb.delete(0, sb.length());
            sb.append("AAAAAAA");
            sb.append("BBBBBBB");
            sb.append("CCCCCCC");
            sb.append("DDDDDDD");
            sb.append("EEEEEEE");
            sb.append("FFFFFFF");
            sb.append("GGGGGGG");
            String a = sb.toString();
        }
        this.delete = System.currentTimeMillis() - startTimeB;//删除字符串所花时间
        //第三种,设置长度
        long startTimeC = System.currentTimeMillis();
        for (int i = 1; i <= 10000000; i++) {
            sb.setLength(0);
            sb.append("AAAAAAA");
            sb.append("BBBBBBB");
            sb.append("CCCCCCC");
            sb.append("DDDDDDD");
            sb.append("EEEEEEE");
            sb.append("FFFFFFF");
            sb.append("GGGGGGG");
            String a = sb.toString();
        }
        this.updateLength = System.currentTimeMillis() - startTimeC;//修改字符串长度所花时间
    }


    public static void main(String[] args) {
        StringBuilderRemove sbr = new StringBuilderRemove();
        long a = 0;
        long b = 0;
        long c = 0;
        for (int i = 0; i < 100; i++) {
            sbr.test();
            a += sbr.newProject;
            b += sbr.delete;
            c += sbr.updateLength;
        }
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
