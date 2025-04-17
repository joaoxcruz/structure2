/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package lapr4.shodrone.utentemanagement.application;

import java.util.Optional;

import lapr4.shodrone.infrastructure.persistence.PersistenceContext;
import lapr4.shodrone.usermanagement.domain.ExemploRoles;
import lapr4.shodrone.utentemanagement.domain.Utente;
import lapr4.shodrone.utentemanagement.domain.MecanographicNumber;
import lapr4.shodrone.utentemanagement.repositories.UtenteRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * @author mcn
 */
public class UtenteService {

	private final AuthorizationService authz = AuthzRegistry.authorizationService();
	private final UtenteRepository repo = PersistenceContext.repositories().utentes();

	public Optional<Utente> findshodroneUtenteByMecNumber(final String mecNumber) {
		authz.ensureAuthenticatedUserHasAnyOf(ExemploRoles.POWER_USER, ExemploRoles.ADMIN, ExemploRoles.OTHER_EXAMPLE);
		return repo.ofIdentity(MecanographicNumber.valueOf(mecNumber));
	}

	public Optional<Utente> findshodroneUtenteByUsername(final Username user) {
		authz.ensureAuthenticatedUserHasAnyOf(ExemploRoles.POWER_USER, ExemploRoles.ADMIN);
		return repo.findByUsername(user);
	}
}
