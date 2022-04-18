package com.br.airsystem.service;

import com.br.airsystem.exception.ForbiddenException;
import com.br.airsystem.model.User;

public abstract class DefaultService {

    protected final String RESOURCE_NOT_FOUND = "Recurso não encontrado.";
    protected final String UNPERMITTED_OPERATION = "Você não possui permissão para executar esta operação.";

    protected void checkPermission(User loggedUser) throws ForbiddenException {
        if(!loggedUser.isAdmin()) throw new ForbiddenException(UNPERMITTED_OPERATION);
    }
}
