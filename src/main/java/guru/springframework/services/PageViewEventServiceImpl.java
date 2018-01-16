package guru.springframework.services;

import guru.springframework.model.events.PageViewEvent;
import guru.springframework.pageview.PageViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class PageViewEventServiceImpl implements PageViewEventService {

    private static final Logger log = LoggerFactory.getLogger(PageViewEventServiceImpl.class);

    private final PageViewService pageViewService;

    @Autowired
    public PageViewEventServiceImpl(final PageViewService pageViewService) {
        this.pageViewService = pageViewService;
    }

    @Override
    public void sendPageViewEvent(final Integer id) {

        final PageViewEvent pageViewEvent = new PageViewEvent();
        pageViewEvent.setPageUrl("springframework.guru/product/" + id);
        pageViewEvent.setPageViewDate(new Date());
        pageViewEvent.setCorrelationId(UUID.randomUUID().toString());

        log.info("Sending Message to page view service");
        pageViewService.sendPageViewEvent(pageViewEvent);
    }

    @Override
    public void sendPageViewEvent() {
        sendPageViewEvent(null);
    }
}
