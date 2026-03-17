package com.cavalcante.never.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object id) {
        super("Erro ao buscar o ID "+id);
    }
}
