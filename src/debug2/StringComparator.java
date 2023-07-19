package debug2;

public class StringComparator implements Comparator<String> {

    @Override
    public boolean compare(String a, String b) {
        if (a.length() < b.length()) {
            return true;
        } else if (a.length() > b.length()) {
            return false;
        } else {
            return (a.compareToIgnoreCase(b) < 0);
        }
    }
}

