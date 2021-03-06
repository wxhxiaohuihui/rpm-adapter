/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 artipie.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.artipie.rpm;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Standard naming policies.
 * @since 0.6
 */
public enum StandardNamingPolicy implements NamingPolicy {
    /**
     * Plain simple names.
     */
    PLAIN((src, digest) -> src),
    /**
     * Add SHA1 prefixes to names.
     */
    SHA1(new HashPrefixed(Digest.SHA1)),
    /**
     * Add SHA256 prefixes to names.
     */
    SHA256(new HashPrefixed(Digest.SHA256));

    /**
     * Origin policy.
     */
    private final NamingPolicy origin;

    /**
     * Enum ctor.
     * @param origin Origin policy
     */
    StandardNamingPolicy(final NamingPolicy origin) {
        this.origin = origin;
    }

    @Override
    public String name(final String source, final Path content) throws IOException {
        return this.origin.name(source, content);
    }
}
