/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.internal;

import java.util.Random;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3i;

public final class MathUtils {
    private static final double DOUBLES_EQUAL_PRECISION = 0.000000001;
    private static final Random RANDOM = new Random();

    private MathUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static AxisAlignedBB boundingBoxByPixels(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        return new AxisAlignedBB(minX / 16f, minY / 16f, minZ / 16f, maxX / 16f, maxY / 16f, maxZ / 16f);
    }

    /**
     * Distance between two {@link Vec3i} (such as {@link net.minecraft.util.math.BlockPos}).
     * Consider using {@link #distanceSq(Vec3i, Vec3i)} when possible.
     */
    public static double distance(Vec3i from, Vec3i to) {
        int dx = to.getX() - from.getX();
        int dy = to.getY() - from.getY();
        int dz = to.getZ() - from.getZ();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     * Distance squared between two {@link Vec3i} (such as {@link net.minecraft.util.math.BlockPos}).
     * Use instead of {@link #distance(Vec3i, Vec3i)} when possible.
     */
    public static double distanceSq(Vec3i from, Vec3i to) {
        int dx = to.getX() - from.getX();
        int dy = to.getY() - from.getY();
        int dz = to.getZ() - from.getZ();
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Distance between two {@link Vec3i} (such as {@link net.minecraft.util.math.BlockPos}), but
     * ignores the Y-coordinate. Consider using {@link #distanceHorizontalSq(Vec3i, Vec3i)} when
     * possible.
     */
    public static double distanceHorizontal(Vec3i from, Vec3i to) {
        int dx = to.getX() - from.getX();
        int dz = to.getZ() - from.getZ();
        return Math.sqrt(dx * dx + dz * dz);
    }

    /**
     * Distance squared between two {@link Vec3i} (such as {@link net.minecraft.util.math.BlockPos}),
     * but ignores the Y-coordinate. Use instead of {@link #distanceHorizontal(Vec3i, Vec3i)} when
     * possible.
     */
    public static double distanceHorizontalSq(Vec3i from, Vec3i to) {
        int dx = to.getX() - from.getX();
        int dz = to.getZ() - from.getZ();
        return dx * dx + dz * dz;
    }

    /**
     * Compare if two doubles are equal, using precision constant {@link #DOUBLES_EQUAL_PRECISION}.
     */
    public static boolean doublesEqual(double a, double b) {
        return doublesEqual(a, b, DOUBLES_EQUAL_PRECISION);
    }

    /**
     * Compare if two doubles are equal, within the given level of precision.
     *
     * @param precision Should be a small, positive number (like {@link #DOUBLES_EQUAL_PRECISION})
     */
    public static boolean doublesEqual(double a, double b, double precision) {
        return Math.abs(b - a) < precision;
    }

    /**
     * Compare if two floats are equal, using precision constant {@link #DOUBLES_EQUAL_PRECISION}.
     */
    public static boolean floatsEqual(float a, float b) {
        return floatsEqual(a, b, (float) DOUBLES_EQUAL_PRECISION);
    }

    /**
     * Compare if two floats are equal, within the given level of precision.
     *
     * @param precision Should be a small, positive number (like {@link #DOUBLES_EQUAL_PRECISION})
     */
    public static boolean floatsEqual(float a, float b, float precision) {
        return Math.abs(b - a) < precision;
    }

    public static boolean inRangeExclusive(double value, double min, double max) {
        return value < max && value > min;
    }

    public static boolean inRangeExclusive(int value, int min, int max) {
        return value < max && value > min;
    }

    public static boolean inRangeInclusive(double value, double min, double max) {
        return value <= max && value >= min;
    }

    public static boolean inRangeInclusive(int value, int min, int max) {
        return value <= max && value >= min;
    }

    public static int min(final int a, final int b) {
        return a < b ? a : b;
    }

    public static int min(int a, final int b, final int c) {
        if (b < a) a = b;
        if (c < a) a = c;
        return a;
    }

    public static int min(int a, final int b, final int c, final int d) {
        if (b < a) a = b;
        if (c < a) a = c;
        if (d < a) a = d;
        return a;
    }

    public static int min(int a, final int b, final int c, final int d, int... rest) {
        int min = min(a, b, c, d);
        for (int i : rest)
            if (i < min)
                min = i;
        return min;
    }

    public static int max(final int a, final int b) {
        return a > b ? a : b;
    }

    public static int max(int a, final int b, final int c) {
        if (b > a) a = b;
        if (c > a) a = c;
        return a;
    }

    public static int max(int a, final int b, final int c, final int d) {
        if (b > a) a = b;
        if (c > a) a = c;
        if (d > a) a = d;
        return a;
    }

    public static int max(int a, final int b, final int c, final int d, int... rest) {
        int max = max(a, b, c, d);
        for (int i : rest)
            if (i > max)
                max = i;
        return max;
    }

    public static double nextGaussian(double mean, double deviation) {
        return deviation * RANDOM.nextGaussian() + mean;
    }

    public static double nextGaussian(Random random, double mean, double deviation) {
        return deviation * random.nextGaussian() + mean;
    }

    public static int nextInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    public static int nextIntInclusive(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }

    public static int nextIntInclusive(Random random, int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static boolean tryPercentage(double percent) {
        return RANDOM.nextDouble() < percent;
    }

    public static boolean tryPercentage(Random random, double percent) {
        return random.nextDouble() < percent;
    }
}
