package General;

public class Utils {

    public static final boolean[] FIRST_COLUMN = createColumnBoolean(0);
    public static final boolean[] SECOND_COLUMN = createColumnBoolean(1);
    public static final boolean[] THIRD_COLUMN = createColumnBoolean(2);
    public static final boolean[] FOURTH_COLUMN = createColumnBoolean(3);
    public static final boolean[] FIFTH_COLUMN = createColumnBoolean(4);
    public static final boolean[] SIXTH_COLUMN = createColumnBoolean(5);
    public static final boolean[] SEVENTH_COLUMN = createColumnBoolean(6);
    public static final boolean[] EIGHTH_COLUMN = createColumnBoolean(7);

    public static final boolean[] FIRST_ROW = createRowBoolean(0);
    public static final boolean[] SECOND_ROW = createRowBoolean(8);
    public static final boolean[] THIRD_ROW = createRowBoolean(16);
    public static final boolean[] FOURTH_ROW = createRowBoolean(24);
    public static final boolean[] FIFTH_ROW = createRowBoolean(32);
    public static final boolean[] SIXTH_ROW = createRowBoolean(40);
    public static final boolean[] SEVENTH_ROW = createRowBoolean(48);
    public static final boolean[] EIGHTH_ROW = createRowBoolean(56);

    public static final boolean[] squareColors = checkerBoard();

    static boolean[] createColumnBoolean(int column) {
        boolean[] b = new boolean[64];
        for (int i = column; i < 64; i += 8) {
            b[i] = true;
        }
        return b;
    }

    static boolean[] createRowBoolean(int row) {
        boolean[] b = new boolean[64];
        for (int i = row; i < row + 8; i++) {
            b[i] = true;
        }
        return b;
    }

    public static boolean validCoordinate(int coordinate) {
        return (!(coordinate > 63 || coordinate < 0));
    }

    static boolean[] checkerBoard() {
        boolean[] r = new boolean[64];
        boolean color = false;
        for (int i = 0; i < 64; i++) {
            if (i % 2 == 0) {
                r[i] = color;
            } else {
                r[i] = !color;
            }
            if (i % 8 == 7) {
                color = !color;
            }
        }
        return r;
    }
}
