/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package griffon.core;

/**
 * Base interface for classes that work with artefacts.
 *
 * @author Andres Almiray (aalmiray)
 */
public interface ArtefactHandler {

    /**
     * Get the tye of artefact this handler processes.
     * The type is normally used as suffix too.
     */
    String getType();

    /**
     * Returns true if the target Class is a class artefact
     * handled by this object.
     */
    boolean isArtefact(Class klass);

    /**
     * Initializes the handler with a collection of all available
     * artefacts this handler can process.<p>
     * This is a good time to pre-emptively instantiate beans or
     * perform additional checks on artefacts.
     */
    void initialize(ArtefactInfo[] artefacts);

    /**
     * Returns an array of all artefacts handled by this object.
     */
    ArtefactInfo[] getArtefacts();

    /**
     * Finds an artefact by name.
     * The name usually matches artefact.simpleName
     */
    ArtefactInfo findArtefact(String name);
}