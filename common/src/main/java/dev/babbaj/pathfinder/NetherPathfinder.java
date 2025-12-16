package dev.babbaj.pathfinder;

/**
 * 运行时补齐的简易占位实现，用于满足 Baritone 依赖的类签名。
 * 这些方法返回安全的默认值，以避免缺失类导致客户端无法启动。
 */
public final class NetherPathfinder {
    public static final int CACHE_MISS_SOLID = 0;

    private NetherPathfinder() {
    }

    public static long newContext(long seed) {
        return 0L;
    }

    public static boolean hasChunkFromJava(long context, int x, int z) {
        return false;
    }

    public static PathSegment pathFind(long context,
                                       int startX, int startY, int startZ,
                                       int goalX, int goalY, int goalZ,
                                       boolean allowDiagonal, boolean allowElytra,
                                       int maxIterations, boolean optimize, int flags) {
        return PathSegment.empty();
    }

    public static boolean isVisible(long context, int mode,
                                    double x1, double y1, double z1,
                                    double x2, double y2, double z2) {
        return false;
    }

    public static int isVisibleMulti(long context, int mode, int count,
                                     double[] from, double[] to, boolean[] results) {
        return -1;
    }

    public static void raytrace(long context, int mode, int count,
                                double[] from, double[] to, boolean[] results, double[] distances) {
        // no-op placeholder
    }

    public static boolean cancel(long context) {
        return true;
    }

    public static void freeContext(long context) {
        // no-op placeholder
    }

    public static boolean isThisSystemSupported() {
        return false;
    }
}


