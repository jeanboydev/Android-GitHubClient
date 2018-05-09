package com.jeanboy.arch.data.repository.handler;

import java.util.ArrayList;
import java.util.List;

public abstract class MapperHandler<From, To> {

    protected abstract To transform(From from);

    public List<To> transform(List<From> fromList) {
        List<To> receivedEventModelList = new ArrayList<>();
        for (int i = 0; i < fromList.size(); i++) {
            To receivedEventModel = transform(fromList.get(i));
            receivedEventModelList.add(receivedEventModel);
        }
        return receivedEventModelList;
    }
}
