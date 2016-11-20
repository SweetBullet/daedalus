package com.bullet.sweet.daedalus.kern.filter;

import com.bullet.sweet.daedalus.kern.RequestContext;
import lombok.Setter;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by zhanlan on 16/11/20.
 */
public class RequestFilterWapper implements Filter<RequestContext> {

    @Setter
    private List<Filter> filters;

    private Filter filter;

    @PostConstruct
    public void init() {
        filter = new BoundedExecutorFilter(new RequestFilters(filters));
    }

    @Override
    public void filter(RequestContext ctx) {
        filter.filter(ctx);
    }



    private static class RequestFilters implements Filter<RequestContext> {

        private Filter<RequestContext> current;

        public RequestFilters(Iterable<Filter> filterIterator) {
            filterIterator.forEach(filter->{
                current = new LinkedFilter(current, filter);
            });
        }

        @Override
        public void filter(RequestContext requestContext) {
            current.filter(requestContext);
        }
    }


}
