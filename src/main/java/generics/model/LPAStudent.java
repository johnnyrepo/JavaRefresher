package generics.model;

import java.util.Comparator;

public class LPAStudent extends Student {

    private double percentComplete;

    public LPAStudent() {
        percentComplete = random.nextDouble(0, 100.001);
    }

    @Override
    public String toString() {
        return "%s %8.1f%%".formatted(super.toString(), percentComplete);
    }

    public double getPercentComplete() {
        return percentComplete;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        if (fieldName.equalsIgnoreCase("PERCENTCOMPLETE")) {
            return (percentComplete <= Integer.parseInt(value));
        }

        return super.matchFieldValue(fieldName, value);
    }

    public static class PercentCompleteComparator implements Comparator<LPAStudent> {
        @Override
        public int compare(LPAStudent ls1, LPAStudent ls2) {
            return Double.compare(ls1.percentComplete, ls2.percentComplete);
        }
    }

}
