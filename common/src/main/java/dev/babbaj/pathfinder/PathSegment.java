package dev.babbaj.pathfinder;

/**
 * 运行时占位的路径结果对象，仅用于避免缺少类导致的崩溃。
 */
public final class PathSegment {
    private static final PathSegment EMPTY = new PathSegment();

    private PathSegment() {
    }

    public static PathSegment empty() {
        return EMPTY;
    }
}

