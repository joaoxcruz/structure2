/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package lapr4.shodrone.infrastructure.bootstrapers.demo;

import java.util.HashSet;
import java.util.Set;

import lapr4.shodrone.infrastructure.bootstrapers.AbstractUserBootstrapper;
import lapr4.shodrone.usermanagement.domain.ExemploRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 * @author Paulo Gandra Sousa
 */
public class BackofficeUsersBootstrapper extends AbstractUserBootstrapper implements Action {

    @SuppressWarnings("squid:S2068")
    private static final String PASSWORD1 = "Password1";

    @Override
    public boolean execute() {
        registerOtherExample("cashier", PASSWORD1, "Johny", "Cash", "johny.doe@emai.l.com");
        registerSample1Manager("kitchen", PASSWORD1, "Oven", "Stove", "Oven.and.stove@emai.l.com");
        registerAnotherExampleManager("chef", PASSWORD1, "Master", "Chef", "master.chef@emai.l.com");
        return true;
    }

    private void registerOtherExample(final String username, final String password,
            final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(ExemploRoles.OTHER_EXAMPLE);

        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerSample1Manager(final String username, final String password,
            final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(ExemploRoles.SAMPLE_1_MANAGER);

        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerAnotherExampleManager(final String username, final String password,
            final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(ExemploRoles.ANOTHER_EXAMPLE_MANAGER);

        registerUser(username, password, firstName, lastName, email, roles);
    }
}
