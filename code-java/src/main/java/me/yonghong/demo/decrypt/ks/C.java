package me.yonghong.demo.decrypt.ks;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class C {
    private static final Charset charset = StandardCharsets.ISO_8859_1;

    public static B a() {
        return B.a;
    }

    public static A b() {
        return A.a;
    }

    public static class A {
        static final A a = new A(false, false);
        static final A b = new A(true, false);
        private static final int[] c = new int[256];
        private static final int[] d = new int[256];
        private final boolean e;
        private final boolean f;

        private A(boolean var1, boolean var2) {
            this.e = var1;
            this.f = var2;
        }

        public byte[] a(byte[] var1) {
            byte[] var2 = new byte[this.a(var1, 0, var1.length)];
            int var3 = this.a(var1, 0, var1.length, var2);
            if (var3 != var2.length) {
                var2 = Arrays.copyOf(var2, var3);
            }

            return var2;
        }

        public byte[] a(String var1) {
            return this.a(var1.getBytes(C.charset));
        }

        private int a(byte[] var1, int var2, int var3) {
            int[] var4 = this.e ? d : c;
            int var5 = 0;
            int var6 = var3 - var2;
            if (var6 == 0) {
                return 0;
            } else if (var6 < 2) {
                if (this.f && var4[0] == -1) {
                    return 0;
                } else {
                    throw new IllegalArgumentException("Input byte[] should at least have 2 bytes for base64 bytes");
                }
            } else {
                if (this.f) {
                    int var7 = 0;

                    while (var2 < var3) {
                        int var8 = var1[var2++] & 255;
                        if (var8 == 61) {
                            var6 -= var3 - var2 + 1;
                            break;
                        }

                        if (var4[var8] == -1) {
                            ++var7;
                        }
                    }

                    var6 -= var7;
                } else if (var1[var3 - 1] == 61) {
                    ++var5;
                    if (var1[var3 - 2] == 61) {
                        ++var5;
                    }
                }

                if (var5 == 0 && (var6 & 3) != 0) {
                    var5 = 4 - (var6 & 3);
                }

                return 3 * ((var6 + 3) / 4) - var5;
            }
        }

        private int a(byte[] var1, int var2, int var3, byte[] var4) {
            int[] var5 = this.e ? d : c;
            int var6 = 0;
            int var7 = 0;
            int var8 = 18;

            while (var2 < var3) {
                int var9 = var1[var2++] & 255;
                if ((var9 = var5[var9]) < 0) {
                    if (var9 == -2) {
                        if ((var8 != 6 || var2 != var3 && var1[var2++] == 61) && var8 != 18) {
                            break;
                        }

                        throw new IllegalArgumentException("Input byte array has wrong 4-byte ending unit");
                    }

                    if (!this.f) {
                        throw new IllegalArgumentException("Illegal base64 character " + Integer.toString(var1[var2 - 1], 16));
                    }
                } else {
                    var7 |= var9 << var8;
                    var8 -= 6;
                    if (var8 < 0) {
                        var4[var6++] = (byte) (var7 >> 16);
                        var4[var6++] = (byte) (var7 >> 8);
                        var4[var6++] = (byte) var7;
                        var8 = 18;
                        var7 = 0;
                    }
                }
            }

            if (var8 == 6) {
                var4[var6++] = (byte) (var7 >> 16);
            } else if (var8 == 0) {
                var4[var6++] = (byte) (var7 >> 16);
                var4[var6++] = (byte) (var7 >> 8);
            } else if (var8 == 12) {
                throw new IllegalArgumentException("Last unit does not have enough valid bits");
            }

            do {
                if (var2 >= var3) {
                    return var6;
                }
            } while (this.f && var5[var1[var2++]] < 0);

            throw new IllegalArgumentException("Input byte array has incorrect ending byte at " + var2);
        }

        static {
            Arrays.fill(c, -1);

            int var0;
            for (var0 = 0; var0 < B.d.length; c[B.d[var0]] = var0++) {
            }

            c[61] = -2;
            Arrays.fill(d, -1);

            for (var0 = 0; var0 < B.e.length; d[B.e[var0]] = var0++) {
            }

            d[61] = -2;
        }
    }

    public static class B {
        static final B a = new B(false, (byte[]) null, -1, true);
        static final B b = new B(true, (byte[]) null, -1, false);
        private static final char[] d = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        private static final char[] e = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};
        private static final byte[] f = new byte[]{13, 10};
        static final B c;
        private final byte[] g;
        private final int h;
        private final boolean i;
        private final boolean j;

        private B(boolean var1, byte[] var2, int var3, boolean var4) {
            this.i = var1;
            this.g = var2;
            this.h = var3;
            this.j = var4;
        }

        private final int a(int var1) {
            int var2;
            if (this.j) {
                var2 = 4 * ((var1 + 2) / 3);
            } else {
                int var3 = var1 % 3;
                var2 = 4 * (var1 / 3) + (var3 == 0 ? 0 : var3 + 1);
            }

            if (this.h > 0) {
                var2 += (var2 - 1) / this.h * this.g.length;
            }

            return var2;
        }

        public byte[] a(byte[] var1) {
            int var2 = this.a(var1.length);
            byte[] var3 = new byte[var2];
            int var4 = this.a(var1, 0, var1.length, var3);
            return var4 != var3.length ? Arrays.copyOf(var3, var4) : var3;
        }

        private int a(byte[] var1, int var2, int var3, byte[] var4) {
            char[] var5 = this.i ? e : d;
            int var6 = var2;
            int var7 = (var3 - var2) / 3 * 3;
            int var8 = var2 + var7;
            if (this.h > 0 && var7 > this.h / 4 * 3) {
                var7 = this.h / 4 * 3;
            }

            int var9 = 0;

            while (true) {
                int var10;
                int var11;
                int var13;
                do {
                    do {
                        if (var6 >= var8) {
                            if (var6 < var3) {
                                var10 = var1[var6++] & 255;
                                var4[var9++] = (byte) var5[var10 >> 2];
                                if (var6 == var3) {
                                    var4[var9++] = (byte) var5[var10 << 4 & 63];
                                    if (this.j) {
                                        var4[var9++] = 61;
                                        var4[var9++] = 61;
                                    }
                                } else {
                                    var11 = var1[var6++] & 255;
                                    var4[var9++] = (byte) var5[var10 << 4 & 63 | var11 >> 4];
                                    var4[var9++] = (byte) var5[var11 << 2 & 63];
                                    if (this.j) {
                                        var4[var9++] = 61;
                                    }
                                }
                            }

                            return var9;
                        }

                        var10 = Math.min(var6 + var7, var8);
                        var11 = var6;

                        for (int var12 = var9; var11 < var10; var4[var12++] = (byte) var5[var13 & 63]) {
                            var13 = (var1[var11++] & 255) << 16 | (var1[var11++] & 255) << 8 | var1[var11++] & 255;
                            var4[var12++] = (byte) var5[var13 >>> 18 & 63];
                            var4[var12++] = (byte) var5[var13 >>> 12 & 63];
                            var4[var12++] = (byte) var5[var13 >>> 6 & 63];
                        }

                        var11 = (var10 - var6) / 3 * 4;
                        var9 += var11;
                        var6 = var10;
                    } while (var11 != this.h);
                } while (var10 >= var3);

                byte[] var16 = this.g;
                var13 = var16.length;

                for (int var14 = 0; var14 < var13; ++var14) {
                    byte var15 = var16[var14];
                    var4[var9++] = var15;
                }
            }
        }

        static {
            c = new B(false, f, 76, true);
        }
    }
}
