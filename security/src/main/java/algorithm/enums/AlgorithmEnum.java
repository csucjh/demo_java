package algorithm.enums;

public enum AlgorithmEnum {
    SHA_1("SHA-1"),
    MD5("MD5");

    private String name;

    AlgorithmEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}