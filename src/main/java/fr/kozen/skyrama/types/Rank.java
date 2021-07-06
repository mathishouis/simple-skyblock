package fr.kozen.skyrama.types;

public enum Rank {
    OWNER(2),
    MEMBER(1),
    GUEST(0);

    public final int rank;

    Rank(int rank) { this.rank = rank; }

    public static Rank fromInt(int id) {
        switch (id) {
            case 2:
                return OWNER;
            case 1:
                return MEMBER;
            default:
                return GUEST;
        }
    }

}
