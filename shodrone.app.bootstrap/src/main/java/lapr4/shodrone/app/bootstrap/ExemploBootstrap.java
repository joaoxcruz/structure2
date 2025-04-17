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
package lapr4.shodrone.app.bootstrap;

import lapr4.shodrone.app.common.console.BaseApp;
import lapr4.shodrone.infrastructure.bootstrapers.ExemploBootstrapper;
import lapr4.shodrone.infrastructure.bootstrapers.demo.DemoBootstrapper;
import lapr4.shodrone.infrastructure.persistence.PersistenceContext;
import lapr4.shodrone.infrastructure.smoketests.DemoSmokeTester;
import lapr4.shodrone.usermanagement.application.eventhandlers.SignupAcceptedWatchDog;
import lapr4.shodrone.usermanagement.domain.ExemploPasswordPolicy;
import lapr4.shodrone.utentemanagement.application.eventhandlers.NewUserRegisteredFromSignupWatchDog;
import lapr4.shodrone.utentemanagement.domain.events.NewUserRegisteredFromSignupEvent;
import lapr4.shodrone.utentemanagement.domain.events.SignupAcceptedEvent;
import eapli.framework.collections.util.ArrayPredicates;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;

/**
 * Bootstrapping data app
 */
@SuppressWarnings("squid:S106")
public final class ExemploBootstrap extends BaseApp {
	/**
	 * avoid instantiation of this class.
	 */
	private ExemploBootstrap() {
	}

	private boolean isToBootstrapDemoData;
	private boolean isToRunSampleE2E;

	public static void main(final String[] args) {

		AuthzRegistry.configure(PersistenceContext.repositories().users(), new ExemploPasswordPolicy(),
				new PlainTextEncoder());

		new ExemploBootstrap().run(args);
	}

	@Override
	protected void doMain(final String[] args) {
		handleArgs(args);

		System.out.println("\n\n------- MASTER DATA -------");
		new ExemploBootstrapper().execute();

		if (isToBootstrapDemoData) {
			System.out.println("\n\n------- DEMO DATA -------");
			new DemoBootstrapper().execute();
		}
		if (isToRunSampleE2E) {
			System.out.println("\n\n------- BASIC SCENARIO -------");
			new DemoSmokeTester().execute();
		}
	}

	private void handleArgs(final String[] args) {
		isToRunSampleE2E = ArrayPredicates.contains(args, "-smoke:basic");
		if (isToRunSampleE2E) {
			isToBootstrapDemoData = true;
		} else {
			isToBootstrapDemoData = ArrayPredicates.contains(args, "-bootstrap:demo");
		}
	}

	@Override
	protected String appTitle() {
		return "Bootstrapping data ";
	}

	@Override
	protected String appGoodbye() {
		return "Bootstrap data done.";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
		dispatcher.subscribe(new NewUserRegisteredFromSignupWatchDog(), NewUserRegisteredFromSignupEvent.class);
		dispatcher.subscribe(new SignupAcceptedWatchDog(), SignupAcceptedEvent.class);
	}
}
