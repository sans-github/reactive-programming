package com.reactive.programming.web.controller;

import com.reactive.programming.web.service.ExternalService;
import com.reactive.programming.web.transformer.RequestEnhancer;
import com.reactive.programming.web.transformer.RequestTransformer;
import com.reactive.programming.web.transformer.ResponseEnhancer;
import com.reactive.programming.web.transformer.ResponseTransformer;
import org.springframework.beans.factory.annotation.Autowired;

public class ReactiveControllerBase {
    @Autowired
    protected RequestTransformer requestTransformer;

    @Autowired
    protected RequestEnhancer requestEnhancer;

    @Autowired
    protected ExternalService externalService;

    @Autowired
    protected ResponseTransformer responseTransformer;

    @Autowired
    protected ResponseEnhancer responseEnhancer;
}
