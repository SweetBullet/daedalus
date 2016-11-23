package com.bullet.sweet.daedalus.kern.processor;

import lombok.Setter;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by zhanlan on 16/11/23.
 */
public class MultiHttpProcessorWrapper implements HttpProcessor {

    @Setter
    private List<HttpAspectProcessor> aspectProcessors;

    private HttpProcessor wrapped;

    @PostConstruct
    public void init() {
        wrapped = new WrappedHttpProcessor(aspectProcessors, ctx -> {
            ctx.getResponse().close();
        });
    }

    @Override
    public void process(HttpContext ctx) {
        wrapped.process(ctx);
    }


    private static class WrappedHttpProcessor implements HttpProcessor {

        private HttpProcessor concurrent;

        public WrappedHttpProcessor(List<HttpAspectProcessor> processors, HttpProcessor processor) {
            concurrent = processor;
            for (HttpAspectProcessor aspectProcessor : processors) {
                concurrent = new MultiHttpProcessor(aspectProcessor, concurrent);
            }
        }

        @Override
        public void process(HttpContext ctx) {
            concurrent.process(ctx);
        }
    }
}

