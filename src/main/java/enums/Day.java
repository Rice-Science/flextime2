package enums;

import lombok.Getter;

@Getter
public enum Day {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String value;
    private Day(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (Day day : Day.values()) {
            if (day.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}