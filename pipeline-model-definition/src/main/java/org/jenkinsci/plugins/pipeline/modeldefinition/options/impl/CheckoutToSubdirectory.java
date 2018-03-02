/*
 * The MIT License
 *
 * Copyright (c) 2017, CloudBees, Inc.
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

package org.jenkinsci.plugins.pipeline.modeldefinition.options.impl;

import hudson.Extension;
import org.jenkinsci.Symbol;
import org.jenkinsci.plugins.pipeline.modeldefinition.options.DeclarativeOption;
import org.jenkinsci.plugins.pipeline.modeldefinition.options.DeclarativeOptionDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CheckoutToSubdirectory extends DeclarativeOption {
    private String subdirectory;

    @DataBoundConstructor
    public CheckoutToSubdirectory(@Nullable String subdirectory) {
        this.subdirectory = subdirectory;
    }

    @CheckForNull
    public String getSubdirectory() {
        return subdirectory;
    }

    @Extension @Symbol("checkoutToSubdirectory")
    public static class DescriptorImpl extends DeclarativeOptionDescriptor {
        @Override
        @Nonnull
        public String getDisplayName() {
            return "Check out SCM to a subdirectory of the workspace";
        }

        @Override
        public boolean canUseInStage() {
            return true;
        }
    }
}
