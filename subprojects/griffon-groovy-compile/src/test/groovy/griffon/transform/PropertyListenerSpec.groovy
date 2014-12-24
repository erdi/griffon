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
package griffon.transform

import spock.lang.Specification

class PropertyListenerSpec extends Specification {
    void "PropertyListener AST transformation attaches a listener"() {
        given:
        String script = '''
        class Bean {
            @griffon.transform.Observable
            @griffon.transform.PropertyListener(snoop)
            String name

            int count = 0

            private snoop = { ++count }
        }
        new Bean()
        '''

        when:
        def bean = new GroovyShell().evaluate(script)
        bean.name = 'griffon'
        bean.name = 'groovy'

        then:
        bean.count == 2
    }
}