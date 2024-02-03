package org.test.domain.factory;

import javax.security.auth.login.AccountNotFoundException;

public interface HandlerFactory <R , C > {

    R handle(C c) throws AccountNotFoundException;
}
