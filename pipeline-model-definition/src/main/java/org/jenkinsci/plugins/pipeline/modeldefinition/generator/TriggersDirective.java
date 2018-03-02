/*
 * The MIT License
 *
 * Copyright (c) 2018, CloudBees, Inc.
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

package org.jenkinsci.plugins.pipeline.modeldefinition.generator;

import hudson.Extension;
import hudson.ExtensionList;
import hudson.model.Descriptor;
import hudson.triggers.Trigger;
import hudson.triggers.TriggerDescriptor;
import org.jenkinsci.plugins.pipeline.modeldefinition.ast.ModelASTPipelineDef;
import org.jenkinsci.plugins.structs.SymbolLookup;
import org.jenkinsci.plugins.structs.describable.UninstantiatedDescribable;
import org.jenkinsci.plugins.workflow.cps.Snippetizer;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class TriggersDirective extends AbstractDirective<TriggersDirective> {
    private List<Trigger> triggers = new ArrayList<>();

    @DataBoundConstructor
    public TriggersDirective(List<Trigger> triggers) {
        if (triggers != null) {
            this.triggers.addAll(triggers);
        }
    }

    @Nonnull
    public List<Trigger> getTriggers() {
        return triggers;
    }

    @Extension
    public static class DescriptorImpl extends DirectiveDescriptor<TriggersDirective> {
        @Override
        @Nonnull
        public String getName() {
            return "triggers";
        }

        @Override
        @Nonnull
        public String getDisplayName() {
            return "Triggers";
        }

        @Override
        @Nonnull
        public List<Descriptor> getDescriptors() {
            List<Descriptor> descriptors = new ArrayList<>();
            for (TriggerDescriptor td : ExtensionList.lookup(TriggerDescriptor.class)) {
                if (!SymbolLookup.getSymbolValue(td).isEmpty()) {
                    descriptors.add(td);
                }
            }

            return descriptors;
        }

        @Override
        @Nonnull
        public String toGroovy(@Nonnull TriggersDirective directive) {
            StringBuilder result = new StringBuilder("triggers {\n");
            for (Trigger trigger : directive.triggers) {
                result.append(Snippetizer.object2Groovy(UninstantiatedDescribable.from(trigger)));
                result.append("\n");
            }
            result.append("}\n");
            return ModelASTPipelineDef.toIndentedGroovy(result.toString());
        }
    }
}
