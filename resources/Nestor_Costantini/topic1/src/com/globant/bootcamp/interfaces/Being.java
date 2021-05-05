package com.globant.bootcamp.interfaces;

import com.globant.bootcamp.enums.Color;

public interface Being<T> {
    T gaveBirth(Color color);
}
