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
package lapr4.shodrone.app.utente.console.presentation;

import lapr4.shodrone.app.common.console.presentation.authz.LoginUI;
import lapr4.shodrone.app.utente.console.presentation.myuser.SignupRequestAction;
import lapr4.shodrone.infrastructure.authz.AuthenticationCredentialHandler;
import lapr4.shodrone.usermanagement.domain.ExemploRoles;
import eapli.framework.actions.ChainedAction;
import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * @author Paulo Gandra Sousa
 */
public class FrontMenu extends AbstractUI {

	private static final int EXIT_OPTION = 0;

	private static final int LOGIN_OPTION = 1;
	private static final int SIGNUP_OPTION = 2;

	@Override
	public boolean show() {
		drawFormTitle();
		return doShow();
	}

	/**
	 * @return true if the user selected the exit option
	 */
	@Override
	public boolean doShow() {
		final var menu = new Menu();
		menu.addItem(LOGIN_OPTION, "Login",
				new ChainedAction(new LoginUI(new AuthenticationCredentialHandler(), ExemploRoles.UTENTE)::show, () -> {
					new MainMenu().mainLoop();
					return true;
				}));
		// TODO: instead of leaving the app, return to the main menu again
		menu.addItem(SIGNUP_OPTION, "Sign up", new SignupRequestAction());
		menu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

		final MenuRenderer renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
		return renderer.render();
	}

	@Override
	public String headline() {
		return "shodrone";
	}
}
