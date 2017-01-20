package genericDeser.util;

public class Second implements Comparable<Second> {
    private int IntValue;
    private boolean BooleanValue;
    private double DoubleValue;

    public Second() {}

    @Override
    public int compareTo(Second other) {
        return IntValue == other.getIntValue() &&
               BooleanValue == other.getBooleanValue() &&
               DoubleValue == other.getDoubleValue()
               ? 0 : 1;
    }

    public void setIntValue(int iIn) {
        IntValue = iIn;
    }

    public void setBooleanValue(boolean sIn) {
        BooleanValue = sIn;
    }

    public void setDoubleValue(double sIn) {
        DoubleValue = sIn;
    }

    public int getIntValue() {
        return IntValue;
    }

    public boolean getBooleanValue() {
        return BooleanValue;
    }

    public double getDoubleValue() {
        return DoubleValue;
    }
}
