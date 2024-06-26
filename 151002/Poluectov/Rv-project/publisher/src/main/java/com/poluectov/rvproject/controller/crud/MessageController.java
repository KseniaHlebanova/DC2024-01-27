package com.poluectov.rvproject.controller.crud;

import com.poluectov.rvproject.dto.message.MessageRequestTo;
import com.poluectov.rvproject.dto.message.MessageResponseTo;
import com.poluectov.rvproject.model.Message;
import com.poluectov.rvproject.service.message.MessageService;
import com.poluectov.rvproject.utils.modelassembler.MessageModelAssembler;
import io.micrometer.common.util.StringUtils;
import io.netty.util.internal.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/messages")
public class MessageController extends CommonRESTController<Message, MessageRequestTo, MessageResponseTo> {
    public MessageController(@Qualifier("cachedMessageService") MessageService service,
                             MessageModelAssembler assembler) {
        super(service, assembler::toModel);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> newEntity(@RequestBody MessageRequestTo request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String country = httpServletRequest.getLocale().getCountry();
        if (StringUtils.isEmpty(country)) {
            country = "locale";
        }

        request.setCountry(country);

        return super.newEntity(request, httpServletRequest, httpServletResponse);
    }
}
