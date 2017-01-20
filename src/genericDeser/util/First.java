package genericDeser.util;

public class First implements Comparable<First> {
    private int IntValue;
    private String StringValue = "";
    private short ShortValue;
    private float FloatValue;

    public First() {}

    @Override
    public int compareTo(First other) {
        return IntValue == other.getIntValue() &&
               StringValue.equals(other.getStringValue()) &&
               ShortValue == other.getShortValue() &&
               FloatValue == other.getFloatValue()
               ? 0 : 1;
    }

    public void setIntValue(int iIn) {
        IntValue = iIn;
    }

    public void setStringValue(String sIn) {
        StringValue = sIn;
    }

    public void setShortValue(short sIn) {
        ShortValue = sIn;
    }

    public void setFloatValue(float fIn) {
        FloatValue = fIn;
    }

    public int getIntValue() {
        return IntValue;
    }

    public String getStringValue() {
        return StringValue;
    }

    public short getShortValue() {
        return ShortValue;
    }

    public float getFloatValue() {
        return FloatValue;
    }

}
