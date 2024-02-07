package com.dgnklz.hrmanagementsystem.cores.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forRequest();
    ModelMapper forResponse();
}
