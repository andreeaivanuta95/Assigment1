package utils;

public enum SliderOption {
    TODAY(1),
    TOMORROW(2),
    THIS_WEEK(3),
    NEXT_WEEK(4);

    private final int value;

    SliderOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
