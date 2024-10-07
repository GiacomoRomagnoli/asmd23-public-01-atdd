package application;

public interface Logic {

    enum CellType {
        STATIC, DYNAMIC, EMPTY
    }

    boolean hit(Position position);

    CellType getMark(Position position);

    boolean isOver();
}
