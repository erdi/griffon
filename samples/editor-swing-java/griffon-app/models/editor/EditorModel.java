/*
 * Copyright 2008-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package editor;

import griffon.core.artifact.GriffonModel;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel;

@ArtifactProviderFor(GriffonModel.class)
public class EditorModel extends AbstractGriffonModel {
    private String mvcIdentifier;
    private Document document;

    public String getMvcIdentifier() {
        return mvcIdentifier;
    }

    public void setMvcIdentifier(String mvcIdentifier) {
        this.mvcIdentifier = mvcIdentifier;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        firePropertyChange("document", this.document, this.document = document);
    }
}