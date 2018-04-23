package com.jeanboy.arch.data.repository.handler;

public interface MapperHandler<From, To> {

    To transform(From from);
}
